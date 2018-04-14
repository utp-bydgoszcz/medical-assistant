package pl.edu.utp.medicalassistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Event {

    @Id
    private String id;
    private String userId;
    private String description;
    private String type;
    private Date date;
    private Location location;
    private List<Rescuer> rescuers;

}
