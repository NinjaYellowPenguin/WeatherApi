package yellowpenguin.weatherapi.dao;

import java.util.List;

import yellowpenguin.weatherapi.models.Weather;

public interface WeatherDao {
	
	public void save(Weather weather);

    public Weather findById(String address);

    public void delete(String address);

    public List<Weather> findAll();

}
