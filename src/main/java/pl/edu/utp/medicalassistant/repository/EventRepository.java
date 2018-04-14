package pl.edu.utp.medicalassistant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.utp.medicalassistant.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
