package weathertogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import weathertogether.app.repository.CitiesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@SpringBootApplication
@EnableMongoRepositories
@Repository
public class Application implements CommandLineRunner {

	@Autowired
    CitiesDataRepository citiesRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
    }

}
