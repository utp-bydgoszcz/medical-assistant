package pl.edu.utp.medicalassistant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    private String id;
    private String userId;
    private String description;
    private String type;
    private Date date;
    private String location;
//    private List<String>

}
