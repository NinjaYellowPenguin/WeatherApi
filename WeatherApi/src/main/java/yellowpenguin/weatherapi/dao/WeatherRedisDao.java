package yellowpenguin.weatherapi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import yellowpenguin.weatherapi.models.Weather;

@Repository
public class WeatherRedisDao implements WeatherDao{

    private static final String REDIS_PREFIX = "Weather:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(Weather weather) {
        redisTemplate.opsForValue().set(REDIS_PREFIX + weather.getAddress(), weather, 10, TimeUnit.MINUTES);
    }

    @Override
    public Weather findById(String address) {
        return (Weather) redisTemplate.opsForValue().get(REDIS_PREFIX + address);
    }

    @Override
    public void delete(String address) {
        redisTemplate.delete(REDIS_PREFIX + address);
    }

    @Override
    public List<Weather> findAll() {
        Set<String> keys = redisTemplate.keys(REDIS_PREFIX + "*");
        List<Weather> penguins = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                Weather weather = (Weather) redisTemplate.opsForValue().get(key);
                if (weather != null) {
                    penguins.add(weather);
                }
            }
        }
        return penguins;
    }

}
