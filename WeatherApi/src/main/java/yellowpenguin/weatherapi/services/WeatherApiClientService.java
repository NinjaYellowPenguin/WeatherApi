package yellowpenguin.weatherapi.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import yellowpenguin.weatherapi.models.Weather;

@Service
public class WeatherApiClientService {

	private final RestTemplate restTemplate;

	@Value("${WEATHER_API_URL}")
	private String apiUrl;

	public WeatherApiClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Weather callApi(String location) {
		String time = getStringDayNow();
		apiUrl = apiUrl.replace("[location]", location);
		apiUrl = apiUrl.replace("[day]", time);
		ResponseEntity<Weather> response = restTemplate.getForEntity(apiUrl, Weather.class);
		return response.getBody();
	}
	
	private String getStringDayNow() {
		LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
