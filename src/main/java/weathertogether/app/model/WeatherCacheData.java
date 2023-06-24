package weathertogether.app.model;
import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "weatherCache")
public class WeatherCacheData {
    @Id
    private String cityID;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private String temperature_scale;
    private LocalDate date;
    private int weathercode;
    private double temperature_2m_max;
    private double temperature_2m_min;
    private double apparent_temperature_max;
    private double apparent_temperature_min;
    private double precipitation_probability_mean;
    private double windspeed_10m_max;


    public WeatherCacheData(
        String cityID,
        String timezone, 
        String timezone_abbreviation, 
        double elevation, 
        String temperature_scale, 
        LocalDate date, 
        int weathercode, 
        double temperature_2m_max, 
        double temperature_2m_min, 
        double apparent_temperature_max, 
        double apparent_temperature_min,
        double precipitation_probability_mean,
        double windspeed_10m_max
        ) {
        this.cityID = cityID;
        this.timezone = timezone;
        this.timezone_abbreviation = timezone_abbreviation;
        this.elevation = elevation;
        this.temperature_scale = temperature_scale;
        this.date = date;
        this.weathercode = weathercode;
        this.temperature_2m_max = temperature_2m_max;
        this.temperature_2m_min = temperature_2m_min;
        this.apparent_temperature_max = apparent_temperature_max;
        this.apparent_temperature_min = apparent_temperature_min;
        this.precipitation_probability_mean = precipitation_probability_mean;
        this.windspeed_10m_max = windspeed_10m_max;
    }

    public String getCityID() {
        return this.cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneAbbreviation() {
        return this.timezone_abbreviation;
    }

    public void setTimezoneAbbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public double getElevation() {
        return this.elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getTemperatureScale() {
        return this.temperature_scale;
    }

    public void setTemperatureScale(String temperature_scale) {
        this.temperature_scale = temperature_scale;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWeathercode() {
        return this.weathercode;
    }

    public void setWeathercode(int weathercode) {
        this.weathercode = weathercode;
    }

    public double getTemperature2mMax() {
        return this.temperature_2m_max;
    }

    public void setTemperature2mMax(double temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public double getTemperature2mMin() {
        return this.temperature_2m_min;
    }

    public void setTemperature2mMin(double temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public double getApparentTemperatureMax() {
        return this.apparent_temperature_max;
    }

    public void setApparentTemperatureMax(double apparent_temperature_max) {
        this.apparent_temperature_max = apparent_temperature_max;
    }

    public double getApparentTemperatureMin() {
        return this.apparent_temperature_min;
    }

    public void setApparentTemperatureMin(double apparent_temperature_min) {
        this.apparent_temperature_min = apparent_temperature_min;
    }

    public double getPrecipitationProbabilityMean() {
        return this.precipitation_probability_mean;
    }

    public void setPrecipitationProbabilityMean(double precipitation_probability_mean) {
        this.precipitation_probability_mean = precipitation_probability_mean;
    }

    public double getWindspeed10mMax() {
        return this.windspeed_10m_max;
    }

    public void setWindspeed10mMax(double windspeed_10m_max) {
        this.windspeed_10m_max = windspeed_10m_max;
    }
}

