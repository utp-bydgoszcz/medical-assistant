package pl.edu.utp.medicalassistant.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.Location;
import pl.edu.utp.medicalassistant.model.mobile.MobileUser;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class MobileService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventService eventService;
	
	public void needHepl(String username, EventType eventType, String description) {
		
	}
	
	public List<MobileUser> getRescuers(String username) {
		return userRepository.findAll()
				.stream()
				.limit(3)
				.map(u -> new MobileUser(u))
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
