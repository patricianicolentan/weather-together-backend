package weathertogether.app.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "cities")
public class CitiesData {
    @Id
    @Field("_id")
    public String _id;
    public String city;
    public Double lat;
    public Double lng;
    public String country;

    public CitiesData(String city, Double lat, Double lng, String country) {
        super();
        this.city = city;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

