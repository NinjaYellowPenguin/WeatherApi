package yellowpenguin.weatherapi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yellowpenguin.weatherapi.models.Weather;
import yellowpenguin.weatherapi.services.WeatherApiClientService;

@SpringBootTest
public class WeatherApiClientTest {
	
	@Autowired
	private WeatherApiClientService service;
	
	@Test
	public void callApiTest() {
		String location = "Barcelona";
		Weather response = service.callApi(location);
		//System.out.println(response);
		assertEquals(response.getAddress().toUpperCase(), location.toUpperCase());
	}

}
