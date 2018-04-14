package pl.edu.utp.medicalassistant.service;

import java.util.List;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventStatus;
import pl.edu.utp.medicalassistant.model.EventType;
import pl.edu.utp.medicalassistant.model.RescuerStatus;

public interface EventService {

	String needHelp(String victomName, EventType eventType, String description);
	
	void changeEventStatus(String eventId, EventStatus eventStatus);
	
	void changeRescuerStatus(String eventId, String rescuesName, RescuerStatus rescuerStatus);
	
	Event findById(String eventId);
	
	List<Event> findAll();
	
	List<Event> findAvailableForUser(String rescuesName);
	
}
