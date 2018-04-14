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

	private UserRepository repository;
	private GeoLocationService geoLocationService;

	private double lat;
	private double lng;
	private String title;
	private String description;
	private EventType type;
	private String address;
	private MobileUser patient;

	public MobileEvent(Event event) {
		this.lat = event.getLocation().getLatitude();
		this.lng = event.getLocation().getLongitude();
		this.title = getNameFromUsername(event.getUserId());
		this.description = event.getDescription();
		this.type = event.getType();
		try {
			this.address = geoLocationService.getAddressFromCords(event.toLatLng());
		} catch (Exception e) {
			throw new CreateMobileEventException("Nie udało się stworzyć zdarzenia.");
		}
		this.patient = getMobileUser(event.getUserId());
	}

	private String getNameFromUsername(String username){
		Optional<User> user = repository.findByUsername(username);
		return user.get().getName();
	}

	private MobileUser getMobileUser(String username){
		Optional<User> user = repository.findByUsername(username);
		return new MobileUser(user.get());
	}
	
	
}
