package yellowpenguin.weatherapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

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
        } catch (HttpClientErrorException e) {
	        if (e.getStatusCode().value() == 404) {
	        	return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error: Location not found");
	        } else {
	        	return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error: There was a problem with the API request: " + e.getMessage());
	        }
	    } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error: Unable to access the API. Check your connection or the service's availability.");
	    } catch (Exception e) {
        	//e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
	    
    }
}