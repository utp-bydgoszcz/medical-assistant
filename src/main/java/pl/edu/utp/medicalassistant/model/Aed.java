package pl.edu.utp.medicalassistant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aed {

    @Id
    private String id;

    private String title;
    private Double lat;
    private Double lon;
}
