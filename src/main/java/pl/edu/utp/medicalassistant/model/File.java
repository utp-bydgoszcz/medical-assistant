package pl.edu.utp.medicalassistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor

public class File {

    @Id
    private String id;
    private byte[] photo;

    public File(byte[] photo){
        this.photo = photo;
    }

}
