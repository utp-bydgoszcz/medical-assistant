package pl.edu.utp.medicalassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	
	
}
