package yellowpenguin.weatherapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yellowpenguin.weatherapi.models.Weather;
import yellowpenguin.weatherapi.services.WeatherService;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String location) {
        try {
            Weather weather = weatherService.getWeather(location);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
        	//e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error: Unable to fetch weather data. Please try again later.");
        }
    }
}