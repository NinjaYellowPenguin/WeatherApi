package yellowpenguin.weatherapi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yellowpenguin.weatherapi.dao.WeatherDao;
import yellowpenguin.weatherapi.models.Weather;

@Service
public class WeatherService {

	@Autowired
	private WeatherDao dao;
	private final int EXPIRATION_TIME = 2;
	@Autowired
	private WeatherApiClientService clientService;

	public Weather getWeather(String location){
		Weather weather = dao.findById(location);
		if (weather == null || weather.getCreatedAt().isBefore(LocalDateTime.now().minusHours(EXPIRATION_TIME))) {
			weather = clientService.callApi(location);
			weather.setCreatedAt(LocalDateTime.now());
			dao.save(weather);						
		}
		return weather;
	}

}
