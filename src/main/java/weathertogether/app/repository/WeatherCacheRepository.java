package weathertogether.app.repository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.Optional;
import weathertogether.app.model.WeatherCacheData;

public interface WeatherCacheRepository extends MongoRepository<WeatherCacheData, String> {
    @Query(value = "{ 'cityID' : ?0, 'date' : ?1 }")
    Optional<WeatherCacheData> findWeatherCacheDataByCityIDAndDate(String cityID, LocalDate date);
}
