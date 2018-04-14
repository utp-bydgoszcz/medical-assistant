package pl.edu.utp.medicalassistant.config;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.url}")
    private String url;

    @Value("${spring.data.mongodb.database}")
    private String db;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return db;
    }


    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(url);
    }

}
