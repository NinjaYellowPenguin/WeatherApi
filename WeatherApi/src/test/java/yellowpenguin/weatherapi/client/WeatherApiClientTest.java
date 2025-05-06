package yellowpenguin.weatherapi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yellowpenguin.weatherapi.models.QueryCostResponse;

@SpringBootTest
public class WeatherApiClientTest {
	
	@Autowired
	private WeatherApiClient service;
	
	@Test
	public void callApiTest() {
		String location = "Barcelona";
		QueryCostResponse response = service.callApi(location);
		System.out.println(response);
		assertEquals(1, 1);
	}

}
