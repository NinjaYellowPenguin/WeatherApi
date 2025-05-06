package yellowpenguin.weatherapi.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import yellowpenguin.weatherapi.models.QueryCostResponse;

@Service
public class WeatherApiClient {

	private final RestTemplate restTemplate;

	@Value("${WEATHER_API_URL}")
	private String apiUrl;

	public WeatherApiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public QueryCostResponse callApi(String location) {
		String time = getStringDayNow();
		apiUrl = apiUrl.replace("[location]", location);
		apiUrl = apiUrl.replace("[day]", time);
		ResponseEntity<QueryCostResponse> response = restTemplate.getForEntity(apiUrl, QueryCostResponse.class);
		return response.getBody();
	}
	
	private String getStringDayNow() {
		LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
