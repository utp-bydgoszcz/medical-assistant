package pl.edu.utp.medicalassistant.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import pl.edu.utp.medicalassistant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventStatus;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.Location;
import pl.edu.utp.medicalassistant.model.Rescuer;
import pl.edu.utp.medicalassistant.repository.EventRepository;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GeoLocationService geoLocationService;
	@Autowired
	private PositionService positionService;
	
	private List<Event> activeEvents = new CopyOnWriteArrayList<>();

	@PostConstruct
	private synchronized void refresh() {
		activeEvents = eventRepository.findAll()
				.stream()
				.filter(e -> e.getStatus() == EventStatus.ACTIVE)
				.collect(Collectors.toList());
	}
	
	@Override
	public String needHelp(String username, EventType eventType, String description) {
		// preparing 
		Location location = positionService.getLocation(username);
		LocalDateTime now = LocalDateTime.now();
		Event event = new Event(null, username, description, eventType, now, location, EventStatus.ACTIVE, new ArrayList<Rescuer>());
		
		event = eventRepository.save(event);
		refresh();
		
		return event.getId();
	}

	@Override
	public void changeEventStatus(String eventId, EventStatus eventStatus) {
		eventRepository.findById(eventId).ifPresent(e -> {
			e.setStatus(eventStatus);
			eventRepository.save(e);
			refresh();
		});
	}

	@Override
	public void changeRescuerStatus(String eventId, String username, EventStatus eventStatus) {
		activeEvents.stream()
				.filter(e -> e.getId().equals(eventId))
				.findAny()
				.ifPresent(event -> {
					
					
				});
	}

	@Override
	public List<Event> findAll() {
		return activeEvents;
	}

	@Override
	public List<Event> findAvailableForUser(String username) {
		return new ArrayList<>();
	}

	@Override
	public Event findById(String eventId) {
		return activeEvents.stream()
				.filter(e -> e.getId().equals(eventId))
				.findAny()
				.orElse(null);
	}
	
	
	
	
}
