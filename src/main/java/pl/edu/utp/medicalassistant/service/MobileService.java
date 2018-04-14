package pl.edu.utp.medicalassistant.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.RescuerStatus;
import pl.edu.utp.medicalassistant.model.mobile.MobileUser;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class MobileService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventService eventService;
	
	
	public List<MobileUser> getRescuers(String eventId) {
		return eventService.findById(eventId)
				.getRescuers()
				.stream()
				.filter(r -> r.getStatus() == RescuerStatus.ASSIGNED)
				.map(r -> findByUsername(r.getRescuerId()))
				.collect(Collectors.toList());
		
	}
	
	public MobileUser findByUsername(String username) {
		return userRepository.findAll()
				.stream()
				.filter(u -> u.getUsername().equals(username))
				.map(u -> new MobileUser(u))
				.findAny()
				.orElse(null);
	}

	
}
