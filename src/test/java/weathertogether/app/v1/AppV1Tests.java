package weathertogether.app.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import weathertogether.app.model.CitiesData;
import weathertogether.app.repository.CitiesDataRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppV1Tests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitiesDataRepository citiesDataRepository;

    @Test
    void testGetAllCities() throws Exception {
        // Mock the data
        List<CitiesData> cities = Arrays.asList(
                new CitiesData("City1", 10.0, 20.0, "Country1"),
                new CitiesData("City2", 30.0, 40.0, "Country2")
        );

        // Mock the repository
        Mockito.when(citiesDataRepository.findAllCityNames()).thenReturn(cities);

        // Mock the API call
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(cities.size()))
                .andExpect(jsonPath("$[0].city").value(cities.get(0).getCity()))
                .andExpect(jsonPath("$[0].lat").value(cities.get(0).getLat()))
                .andExpect(jsonPath("$[0].lng").value(cities.get(0).getLng()))
                .andExpect(jsonPath("$[0].country").value(cities.get(0).getCountry()))
                .andExpect(jsonPath("$[1].city").value(cities.get(1).getCity()))
                .andExpect(jsonPath("$[1].lat").value(cities.get(1).getLat()))
                .andExpect(jsonPath("$[1].lng").value(cities.get(1).getLng()))
                .andExpect(jsonPath("$[1].country").value(cities.get(1).getCountry()));

        // Verify mock repository was called
        Mockito.verify(citiesDataRepository).findAllCityNames();
    }

    @Test
    void testGetCitiesByCityAndCountry() throws Exception {
        // Mock the data
        List<CitiesData> cities = Arrays.asList(
                new CitiesData("City1", 10.0, 20.0, "Country1"),
                new CitiesData("City2", 30.0, 40.0, "Country2")
        );

        // Mock the repository
        Mockito.when(citiesDataRepository.findCityByCityAndCountry("City1", "Country1")).thenReturn(cities);

        // Mock the API call
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cities/coordinates")
                .param("city", "City1")
                .param("country", "Country1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(cities.size()))
                .andExpect(jsonPath("$[0].city").value(cities.get(0).getCity()))
                .andExpect(jsonPath("$[0].lat").value(cities.get(0).getLat()))
                .andExpect(jsonPath("$[0].lng").value(cities.get(0).getLng()))
                .andExpect(jsonPath("$[0].country").value(cities.get(0).getCountry()))
                .andExpect(jsonPath("$[1].city").value(cities.get(1).getCity()))
                .andExpect(jsonPath("$[1].lat").value(cities.get(1).getLat()))
                .andExpect(jsonPath("$[1].lng").value(cities.get(1).getLng()))
                .andExpect(jsonPath("$[1].country").value(cities.get(1).getCountry()));

        // Verify mock repository was called
        Mockito.verify(citiesDataRepository).findCityByCityAndCountry("City1", "Country1");
    }
}
