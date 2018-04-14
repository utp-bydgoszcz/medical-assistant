package pl.edu.utp.medicalassistant.model.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileEvent {

	private double lat;
	private double lng;
	private String title;
	private String description;
	private EventType type;
	private String address;
	private MobileUser patient;

	public MobileEvent(Event event) {
		
	}
	
	
	
}
