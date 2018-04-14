package pl.edu.utp.medicalassistant.service.impl;

import java.util.ArrayList;
import java.util.List;
import pl.edu.utp.medicalassistant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventStatus;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public String needHelp(String username, EventType eventType, String description) {
		return "#1";
	}

	@Override
	public void changeEventStatus(String eventId, EventStatus eventStatus) {
	}

	@Override
	public void changeRescuerStatus(String eventId, String username, EventStatus eventStatus) {
	}

	@Override
	public List<Event> findAll() {
		return new ArrayList<>();
	}

	@Override
	public List<Event> findAvailableForUser(String username) {
		return new ArrayList<>();
	}
	
	
	
	
}
