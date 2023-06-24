package weathertogether.app.model;
import java.util.HashMap;
import java.util.Map;


public class WeatherCode {
    private Map<Integer, String> codes;

    public WeatherCode() {
        this.codes = new HashMap<>();
        this.populateCodes();
    }

    public String getDescription(Integer code) {
        return this.codes.get(code);
    }

    public void populateCodes() {
        this.codes.put(0, "Clear sky");
        this.codes.put(1, "Mainly clear");
        this.codes.put(2, "Partly cloudy");
        this.codes.put(3, "Overcast");
        this.codes.put(45, "Fog");
        this.codes.put(48, "Depositing rime fog");
        this.codes.put(51, "Drizzle: Light intensity");
        this.codes.put(53, "Drizzle: Moderate intensity");
        this.codes.put(55, "Drizzle: Dense intensity");
        this.codes.put(56, "Freezing Drizzle: Light intensity");
        this.codes.put(57, "Freezing Drizzle: Dense intensity");
        this.codes.put(61, "Rain: Slight intensity");
        this.codes.put(63, "Rain: Moderate intensity");
        this.codes.put(65, "Rain: Heavy intensity");
        this.codes.put(66, "Freezing Rain: Light intensity");
        this.codes.put(67, "Freezing Rain: Heavy intensity");
        this.codes.put(71, "Snow fall: Slight intensity");
        this.codes.put(73, "Snow fall: Moderate intensity");
        this.codes.put(75, "Snow fall: Heavy intensity");
        this.codes.put(77, "Snow grains");
        this.codes.put(80, "Rain showers: Slight intensity");
        this.codes.put(81, "Rain showers: Moderate intensity");
        this.codes.put(82, "Rain showers: Violent intensity");
        this.codes.put(85, "Snow showers: Slight intensity");
        this.codes.put(86, "Snow showers: Heavy intensity");
        this.codes.put(95, "Thunderstorm: Slight or moderate");
        this.codes.put(96, "Thunderstorm with slight hail");
        this.codes.put(99, "Thunderstorm with heavy hail");
    }
}

