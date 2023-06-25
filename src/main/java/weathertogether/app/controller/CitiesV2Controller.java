package weathertogether.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import weathertogether.app.repository.WeatherCacheRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;

import weathertogether.app.model.WeatherCacheData;
import java.util.Optional;

@RestController
@RequestMapping("/v2")
public class CitiesV2Controller {
    @Autowired
    private WeatherCacheRepository weatherRepo;
    
    @RequestMapping("/ping")
    public String checkHealth() {
        LocalDate currentDate = LocalDate.now();
        return "Server working at " + currentDate.toString();
    }

    @GetMapping("/weather")
    public WeatherCacheData getWeatherByLatAndLng(@RequestParam("cityID") String cityID, 
                                                @RequestParam("lat") String lat, 
                                                @RequestParam("lng") String lng) {

        // Check cache for the city's forecast today
        LocalDate currentDate = LocalDate.now();
        Optional<WeatherCacheData> optionalWeatherData = weatherRepo.findWeatherCacheDataByCityIDAndDate(cityID, currentDate);        

        // Return cached response if found
        if (optionalWeatherData.isPresent()) {
             WeatherCacheData cachedResponse = optionalWeatherData.get();
            return cachedResponse;
        }

        // If no cached response, fetch forecast from API        
        WebClient webClient = WebClient.create();
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,precipitation_probability_mean,windspeed_10m_max&forecast_days=1&timezone=auto", lat, lng);
        Map<String, Object> weatherData = null;

        try {
            weatherData = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (WebClientResponseException ex) {
            System.err.println("API error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            System.err.println("Unexpected error: " + ex.getMessage());
        }

        // Create response object
        String weatherDataJson = new JSONObject(weatherData).toString();
        JSONObject jsonObject = new JSONObject(weatherDataJson);
        JSONObject dailyUnits = jsonObject.getJSONObject("daily_units");
        JSONObject daily = jsonObject.getJSONObject("daily");
        JSONArray timeArray = daily.getJSONArray("time");
        JSONArray weatherCodeArray = daily.getJSONArray("weathercode");
        JSONArray tempMaxArray = daily.getJSONArray("temperature_2m_max");
        JSONArray tempMinArray = daily.getJSONArray("temperature_2m_min");
        JSONArray appTempMaxArray = daily.getJSONArray("apparent_temperature_max");
        JSONArray appTempMinArray = daily.getJSONArray("apparent_temperature_min");
        JSONArray precipitationArray = daily.getJSONArray("precipitation_probability_mean");
        JSONArray windspeedArray = daily.getJSONArray("windspeed_10m_max");
        
        WeatherCacheData response = new WeatherCacheData(
            cityID,
            weatherData.get("timezone").toString(),
            weatherData.get("timezone_abbreviation").toString(),
            Double.valueOf(weatherData.get("elevation").toString()),
            dailyUnits.getString("temperature_2m_max"),
            LocalDate.parse(timeArray.getString(0)),
            weatherCodeArray.getInt(0),
            tempMaxArray.getInt(0),
            tempMinArray.getInt(0),
            appTempMaxArray.getInt(0),
            appTempMinArray.getInt(0),
            precipitationArray.getInt(0),
            windspeedArray.getInt(0)
        );

        // Cache response in database and return it
        weatherRepo.save(response);
        return response;
    }
}
