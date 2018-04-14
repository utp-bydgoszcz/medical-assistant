package pl.edu.utp.medicalassistant.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.Location;

@Service
public class PositionService {

	private final Map<String, Location> map = new ConcurrentHashMap<>();

	public void setLocation(Location location, String name){
		if(map.containsKey(name))
			map.replace(name, location);
		else
			map.put(name, location);
	}

	public Location getLocation(String name){
		if(map.containsKey(name))
			return map.get(name);
		else
			throw new
	}
	
}
