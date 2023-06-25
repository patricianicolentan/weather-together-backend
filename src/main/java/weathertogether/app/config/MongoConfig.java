package weathertogether.app.config;

import com.google.appengine.api.utils.SystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Bean;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private final Environment environment;

    @Autowired
    public MongoConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected String getDatabaseName() {
        return "weatherTogether";
    }

    @Override
    public MongoClient mongoClient() {
        // To run locally, add env vars for MongoDB credentials
        String mongodbUsername = SystemProperty.environment.get("MONGODB_USERNAME");
        String mongodbPassword = SystemProperty.environment.get("MONGODB_PASSWORD");
        String formattedString = "mongodb+srv://" + username + ":" + password + "@weathertogether.dofg3ns.mongodb.net/";
        
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
