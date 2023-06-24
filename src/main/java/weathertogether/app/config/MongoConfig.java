package weathertogether.app.config;

import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @Override
    protected String getDatabaseName() {
        return "weatherTogether";
    }

    public static class AppConfig {
        private Map<String, String> env_variables;
        private String runtime;
        private String instance_class;
        private Map<String, String> automatic_scaling;

        public AppConfig() {
        }

        public Map<String, String> getEnv_variables() {
            return env_variables;
        }

        public void setEnv_variables(Map<String, String> env_variables) {
            this.env_variables = env_variables;
        }

        public String getRuntime() {
            return runtime;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public String getInstance_class() {
            return instance_class;
        }

        public void setInstance_class(String instance_class) {
            this.instance_class = instance_class;
        }

        public Map<String, String> getAutomatic_scaling() {
            return automatic_scaling;
        }

        public void setAutomatic_scaling(Map<String, String> automatic_scaling) {
            this.automatic_scaling = automatic_scaling;
        }
    }

    @Override
    public MongoClient mongoClient() {
        Yaml yaml = new Yaml();
        Map<String, String> envVariables = null;
        try (InputStream inputStream = new FileInputStream("src/main/appengine/app.yaml")) {
            AppConfig appConfig = yaml.loadAs(inputStream, AppConfig.class);
            envVariables = appConfig.getEnv_variables();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mongodbUsername = envVariables.get("MONGODB_USERNAME");
        String mongodbPassword = envVariables.get("MONGODB_PASSWORD");
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
