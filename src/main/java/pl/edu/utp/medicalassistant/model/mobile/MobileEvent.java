package pl.edu.utp.medicalassistant.model.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.utp.medicalassistant.exception.CreateMobileEventException;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.repository.UserRepository;
import pl.edu.utp.medicalassistant.service.GeoLocationService;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileEvent {


	private String eventId;
	private double lat;
	private double lng;
	private String title;
	private String description;
	private EventType type;
	private String address;
	private MobileUser patient;

	public MobileEvent(Event event, String title, String address, MobileUser patient) {
		this.lat = event.getLocation().getLatitude();
		this.lng = event.getLocation().getLongitude();
		this.title = title;
		this.description = event.getDescription();
		this.type = event.getType();
		this.address = address;
		this.patient = patient;
	}


	
	
}
