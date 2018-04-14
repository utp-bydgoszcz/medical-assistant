package pl.edu.utp.medicalassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.Location;
import pl.edu.utp.medicalassistant.model.mobile.MobileUser;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class Mobileservice {

	@Autowired
	private UserRepository userRepository;
	
	public void needHepl(String username, EventType eventType, String description) {
		
	}
	
	public MobileUser findByUsername(String username) {
		return userRepository.findAll()
				.stream()
				.filter(u -> u.getUsername().equals(username))
				.map(u -> new MobileUser(u))
				.findAny()
				.orElse(null);
	}

	public boolean cancelR
	
}
