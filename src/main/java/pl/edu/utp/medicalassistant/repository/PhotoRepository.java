package pl.edu.utp.medicalassistant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.utp.medicalassistant.model.Photo;

import java.util.Optional;

@Repository
public interface PhotoRepository extends MongoRepository<Photo,String> {

    Optional<Photo> findByUsernameId(String usernameId);
}
