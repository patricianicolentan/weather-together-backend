package weathertogether.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import weathertogether.app.repository.CitiesDataRepository;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import weathertogether.app.model.CitiesData;


@RestController
@RequestMapping("/v1/cities")
public class CitiesV1Controller {
    @Autowired
    private CitiesDataRepository citiesRepo;

    @GetMapping
    public List<CitiesData> getAllCities() {
        return citiesRepo.findAllCityNames();
    }

    @GetMapping("/coordinates")
    public List<CitiesData> getCitiesByCityAndCountry(@RequestParam("city") String city, @RequestParam("country") String country) {
        return citiesRepo.findCityByCityAndCountry(city, country);
    }

}
