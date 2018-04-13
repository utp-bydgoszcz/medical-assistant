package pl.edu.utp.medicalassistant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String photoId;
    private String configuration;
    private String notification;

    public User(String name, String photoId, String configuration, String notification){
        this.name = name;
        this.photoId = photoId;
        this.configuration = configuration;
        this.notification = notification;
    }

}
