package weathertogether.app.v2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import weathertogether.app.controller.CitiesV2Controller;
import weathertogether.app.repository.WeatherCacheRepository;
import weathertogether.app.model.WeatherCacheData;


@SpringBootTest
@AutoConfigureMockMvc
class AppV2Tests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CitiesV2Controller citiesV2Controller;

    @Test
    void contextLoads() {
        // Verify that the application context loads
    }

	@Test
    void testCheckHealthEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/ping"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("Server working at " + LocalDate.now().toString()));
    }

    @Test
    void testGetWeatherByLatAndLng() throws Exception {
		LocalDate currentDate = LocalDate.now();
		
		// Mock the repository
		WeatherCacheRepository weatherCacheRepository = mock(WeatherCacheRepository.class);
		when(weatherCacheRepository.findWeatherCacheDataByCityIDAndDate("647c4c35cbefb6c29b8eb874", currentDate))
			.thenReturn(Optional.empty());

		// Make the API call
		WeatherCacheData actualWeatherData = citiesV2Controller.getWeatherByLatAndLng(
				"647c4c35cbefb6c29b8eb874",
				"11.9667",
				"50.7500"
		);

		// Check response
		assertThat(actualWeatherData).isNotNull();
		assertThat(actualWeatherData.getCityID()).isNotNull();
		assertThat(actualWeatherData.getTimezone()).isNotNull();
		assertThat(actualWeatherData.getTimezoneAbbreviation()).isNotNull();
		assertThat(actualWeatherData.getElevation()).isNotNull();
		assertThat(actualWeatherData.getTemperatureScale()).isNotNull();
		assertThat(actualWeatherData.getDate()).isNotNull();
		assertThat(actualWeatherData.getWeathercode()).isNotNull();
		assertThat(actualWeatherData.getTemperature2mMax()).isNotNull();
		assertThat(actualWeatherData.getTemperature2mMin()).isNotNull();
		assertThat(actualWeatherData.getApparentTemperatureMax()).isNotNull();
		assertThat(actualWeatherData.getApparentTemperatureMin()).isNotNull();
		assertThat(actualWeatherData.getPrecipitationProbabilityMean()).isNotNull();
		assertThat(actualWeatherData.getWindspeed10mMax()).isNotNull();
	}
}
