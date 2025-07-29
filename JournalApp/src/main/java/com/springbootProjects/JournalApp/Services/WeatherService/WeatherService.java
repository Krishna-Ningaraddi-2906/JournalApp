package com.springbootProjects.JournalApp.Services.WeatherService;
import com.springbootProjects.JournalApp.ApiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService
{
    private static final String API_KEY = "29ed77916756429186960832252907";

    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city)
    {
        String finalUrl = API_URL.replace("CITY", city).replace("API_KEY", API_KEY);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);

        return response.getBody();


    }

}
