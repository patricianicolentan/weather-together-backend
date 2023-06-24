package weathertogether.app.repository;
import weathertogether.app.model.CitiesData;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CitiesDataRepository extends MongoRepository<CitiesData, String> {
    @Query(value = "{}", fields = "{ 'city' : 1, 'country' : 1 }")
    List<CitiesData> findAllCityNames();

    @Query(value = "{ 'city' : ?0, 'country' : ?1 }")
    List<CitiesData> findCityByCityAndCountry(String city, String country);
}
