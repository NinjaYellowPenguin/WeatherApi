package yellowpenguin.weatherapi.client;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import yellowpenguin.weatherapi.dao.WeatherDao;
import yellowpenguin.weatherapi.models.Weather;
import yellowpenguin.weatherapi.services.WeatherApiClientService;
import yellowpenguin.weatherapi.services.WeatherService;

import java.time.LocalDateTime;

@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherDao mockDao;

    @Mock
    private WeatherApiClientService mockClientService;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather_ReturnsCachedData_WhenNotExpired() throws Exception {
        // Arrange
        String location = "New York";
        Weather cachedWeather = new Weather();
        cachedWeather.setCreatedAt(LocalDateTime.now().minusHours(3)); // Dentro del tiempo de expiración
        when(mockDao.findById(location)).thenReturn(cachedWeather);

        Weather result = weatherService.getWeather(location);

        assertNotNull(result);
        assertEquals(cachedWeather, result);
        verify(mockClientService, never()).callApi(anyString()); // No se debe llamar a la API
        verify(mockDao, never()).save(any(Weather.class)); // No se debe guardar nuevos datos
    }

    @Test
    public void testGetWeather_UpdatesCache_WhenDataIsExpired() throws Exception {
        // Arrange
        String location = "New York";
        Weather expiredWeather = new Weather();
        expiredWeather.setCreatedAt(LocalDateTime.now().minusHours(10)); // Datos expirados
        Weather updatedWeather = new Weather();
        updatedWeather.setCreatedAt(LocalDateTime.now());

        when(mockDao.findById(location)).thenReturn(expiredWeather);
        when(mockClientService.callApi(location)).thenReturn(updatedWeather);

        // Act
        Weather result = weatherService.getWeather(location);

        // Assert
        assertNotNull(result);
        assertEquals(updatedWeather, result);
        verify(mockClientService, times(1)).callApi(location);
        verify(mockDao, times(1)).save(updatedWeather); // Los datos actualizados deben guardarse en la caché
    }

    @Test
    public void testGetWeather_CallsApi_WhenNoCacheDataExists() throws Exception {
        // Arrange
        String location = "Los Angeles";
        Weather newWeather = new Weather();
        newWeather.setCreatedAt(LocalDateTime.now());

        when(mockDao.findById(location)).thenReturn(null); // Sin datos en caché
        when(mockClientService.callApi(location)).thenReturn(newWeather);

        // Act
        Weather result = weatherService.getWeather(location);

        // Assert
        assertNotNull(result);
        assertEquals(newWeather, result);
        verify(mockClientService, times(1)).callApi(location); // Se debe llamar a la API
        verify(mockDao, times(1)).save(newWeather); // Se deben guardar los nuevos datos en la caché
    }

    @Test
    public void testGetWeather_ThrowsException_WhenApiFails() {
        // Arrange
        String location = "London";
        when(mockDao.findById(location)).thenReturn(null); // Sin datos en caché
        when(mockClientService.callApi(location)).thenThrow(new RuntimeException("API error")); // Error en la API

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            weatherService.getWeather(location);
        });
        assertEquals("Third party API is not available.", exception.getMessage());
        verify(mockDao, never()).save(any(Weather.class)); // No se deben guardar datos
    }
}
