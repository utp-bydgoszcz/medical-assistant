package pl.edu.utp.medicalassistant.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class Event {

    @Id
    private String id;
    private String userId;
    private String description;
    private EventType type;
    private LocalDateTime date;
    private Location location;
    private EventStatus status;
	private List<Rescuer> rescuers;
	

}
