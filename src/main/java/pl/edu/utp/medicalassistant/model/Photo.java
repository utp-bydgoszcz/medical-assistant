package pl.edu.utp.medicalassistant.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Photo {

    @Id
    private String id;

    private String usernameId;

    private byte[] file;

    public Photo(byte[] file,String usernameId) {
        this.file = file;
        this.usernameId = usernameId;
    }
}
