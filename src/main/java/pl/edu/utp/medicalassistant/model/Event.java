package pl.edu.utp.medicalassistant.model;

import java.time.LocalDateTime;

import com.google.maps.model.LatLng;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

//	public Event(String username, ){
//
//    }

	public LatLng toLatLng(){
	    return location.toLatLng();
    }
	

}
