package weathertogether.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Bean;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;


@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "weatherTogether";
    }

    @Override
    public MongoClient mongoClient() {
        // To run locally, add env vars for MongoDB credentials
        Dotenv dotenv = Dotenv.configure().load();
        String mongodbUsername = dotenv.get("MONGODB_USERNAME");
        String mongodbPassword = dotenv.get("MONGODB_PASSWORD");
        String formattedString = "mongodb+srv://" + mongodbUsername + ":" + mongodbPassword + "@weathertogether.dofg3ns.mongodb.net/";
        
        ConnectionString connectionString = new ConnectionString(formattedString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
