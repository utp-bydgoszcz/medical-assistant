package pl.edu.utp.medicalassistant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.utp.medicalassistant.model.Aed;

public interface AedRepostiory extends MongoRepository<Aed,String> {

}
