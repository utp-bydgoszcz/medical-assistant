package pl.edu.utp.medicalassistant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.Location;
import pl.edu.utp.medicalassistant.model.Rescuer;
import pl.edu.utp.medicalassistant.model.RescuerStatus;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.model.web.Information;
import pl.edu.utp.medicalassistant.model.web.InformationPerson;
import pl.edu.utp.medicalassistant.model.web.InformationType;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class WebInformationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PositionService positionService;
	@Autowired
	private GeoLocationService geoLocationService;
	@Autowired
	private EventService eventService;
	

//	private List<Information> mockList = new ArrayList<>();

	private final Random random = new Random();

	public List<Information> getInformations() {
		List<Information> informations = new ArrayList<>();
		
		// events
		eventService.findAll()
				.forEach(e -> {
				
					User p = userRepository.findByUsername(e.getUserId()).get();
					InformationPerson ip = new InformationPerson(p);
					
					List<InformationPerson> ips = new ArrayList<>();
					if (e.getRescuers() != null) {
						for (Rescuer r : e.getRescuers()) {
							if (r.getStatus() == RescuerStatus.ASSIGNED) {
								User pr = userRepository.findByUsername(r.getRescuerId()).get();
								InformationPerson ipr = new InformationPerson(pr);
								ips.add(ipr);
							}
						}
					}
					
					InformationType type = e.getType().toInformationType();
					Information information = new Information(e.getLocation().getLatitude(), e.getLocation().getLongitude(), e.getType().toString(), e.getDescription(), type, "", ip, ips);
					informations.add(information);
					
						
				});
		
		// users
		userRepository.findAll()
				.forEach(u -> {
				
					// TOOD add address
					InformationPerson person = new InformationPerson(u);
					Location location = positionService.getLocation(u.getUsername());
					InformationType type = u.getType().toInformationType();
					Information information = new Information(location.getLatitude(), location.getLongitude(), u.getName(), "", type, "", person, null);
					informations.add(information);
					
				});
		
		return informations;
	}

	@Scheduled(fixedRate = 1000)
	private void randomPosition() {

//		List<String> users = Arrays.asList("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8");
		List<String> users = Arrays.asList("user2", "user3", "user5", "user6", "user7", "user8");
		for (String user : users) {

			try {
				Location l = positionService.getLocation(user);
				l.setLatitude(l.getLatitude() + (random.nextDouble() - 0.5) / 10000);
				l.setLongitude(l.getLongitude() + (random.nextDouble() - 0.5) / 10000);
			} catch (Exception e) {

			}

		}
	}

	@PostConstruct
	public void initMockData() {

		positionService.setLocation(new Location(53.126182, 18.0007787, 0, 0), "user1");
		positionService.setLocation(new Location(53.128654, 17.988419, 0, 0), "user2");
		positionService.setLocation(new Location(53.121487, 17.997665, 0, 0), "user3");
		positionService.setLocation(new Location(53.132774, 18.007817, 0, 0), "user4");
		positionService.setLocation(new Location(53.126182, 18.0007787, 0, 0), "user5");
		positionService.setLocation(new Location(53.128654, 17.988419, 0, 0), "user6");
		positionService.setLocation(new Location(53.121487, 17.997665, 0, 0), "user7");
		positionService.setLocation(new Location(53.132413, 17.990393, 0, 0), "user8");

//		InformationPerson p1 = new InformationPerson("Jan Kolwalski", "Uważać", "Wykwalifikowany ratownik medyczny", "user1", "+800123456");
//		InformationPerson p2 = new InformationPerson("Karol Kolwalski", "", "Pomagam", "user1", "+800123456");
//		InformationPerson p3 = new InformationPerson("Anna Nowak", "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pomagam", "user1", "+800123456");
//		InformationPerson p4 = new InformationPerson("Paweł Nowak", "", "Pomagam", "user1", "+800123456");
//		InformationPerson p5 = new InformationPerson("Artur Smith",  "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pamagam przechodzić przez ulice", "user1", "+800123456");
//		InformationPerson p6 = new InformationPerson("Karolina Kowalska", "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pomagam", "user1", "+800123456");
//		InformationPerson p7 = new InformationPerson("Karolina Kowalska", "", "", "user1", "+800123456");
//		
//		mockList.add(new Information(53.121487, 17.997665, "Zagrożenie życia czlowieka", "", InformationType.DANGER_LIFE, "", p3, Arrays.asList(p2)));
//		mockList.add(new Information(53.124946, 18.010048, "Wypadek samochodowy", "Kolizja", InformationType.DANGER_LIFE, "", p6, Arrays.asList(p2, p3, p4)));
//		mockList.add(new Information(53.132413, 17.990393, "Złamana noga", "", InformationType.DANGER_HEALTH, "", p3, Arrays.asList(p2, p7)));
//		mockList.add(new Information(53.132774, 18.007817, "Skaleczenie", "", InformationType.DANGER_LIFE, "", p3, Arrays.asList(p2)));
//		mockList.add(new Information(53.129787, 18.010992, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP, "", p4, Arrays.asList(p2, p3)));
//		mockList.add(new Information(53.124122, 17.982840, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP, "", p3, Arrays.asList(p2, p5)));
//		mockList.add(new Information(53.126182, 18.0007787, "Stłuczka", "", InformationType.OTHER, "", p3, Arrays.asList(p2)));
//
//		mockList.add(new Information(53.126182, 18.0007787, "Jan Kolwalski", "", InformationType.MEDICAL_RESCUER, "", p1, null));
//		mockList.add(new Information(53.128654, 17.988419, "Karol Kolwalski", "", InformationType.MEDICAL_RESCUER, "", p1, null));
//		mockList.add(new Information(53.128654, 17.988419, "Anna Nowak", "", InformationType.RESCUER, "", p1, null));
//		mockList.add(new Information(53.128654, 17.988419, "Paweł Nowak", "", InformationType.OTHER_PERSON, "", p1, null));
//		mockList.add(new Information(53.126594, 17.997946, "Artur Smith", "", InformationType.OTHER_PERSON, "", p1, null));
//		mockList.add(new Information(53.127006, 18.004383, "Karolina Kowalska", "", InformationType.OTHER_PERSON, "", p1, null));
//		mockList.add(new Information(53.127109, 18.001637, "Kasia Pawlak", "", InformationType.OTHER_PERSON, "", p1, null));
	}

}
