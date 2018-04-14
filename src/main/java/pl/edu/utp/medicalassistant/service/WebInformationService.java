package pl.edu.utp.medicalassistant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.web.Information;
import pl.edu.utp.medicalassistant.model.web.InformationPerson;
import pl.edu.utp.medicalassistant.model.web.InformationType;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@Service
public class WebInformationService {

	@Autowired
	private UserRepository userRepository;
	
	private List<Information> mockList = new ArrayList<>();

	private final Random random = new Random();

	public List<Information> getInformations() {
		return mockList;
	}
	
	@Scheduled(fixedRate = 1000)
	private void randomPosition() {

		mockList.forEach(i -> {

			i.setLat(i.getLat() + (random.nextDouble() - 0.5) / 10000);
			i.setLng(i.getLng() + (random.nextDouble() - 0.5) / 10000);
		});
	}

	@PostConstruct
	public void initMockData() {

		InformationPerson p1 = new InformationPerson("Jan Kolwalski", "Uważać", "Wykwalifikowany ratownik medyczny", "user1", "+800123456");
		InformationPerson p2 = new InformationPerson("Karol Kolwalski", "", "Pomagam", "user1", "+800123456");
		InformationPerson p3 = new InformationPerson("Anna Nowak", "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pomagam", "user1", "+800123456");
		InformationPerson p4 = new InformationPerson("Paweł Nowak", "", "Pomagam", "user1", "+800123456");
		InformationPerson p5 = new InformationPerson("Artur Smith",  "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pamagam przechodzić przez ulice", "user1", "+800123456");
		InformationPerson p6 = new InformationPerson("Karolina Kowalska", "Niepełnosprawny \n brane leki: witamina C \n choroby przewlekłe: cukrzyca", "Pomagam", "user1", "+800123456");
		InformationPerson p7 = new InformationPerson("Karolina Kowalska", "", "", "user1", "+800123456");
		
		mockList.add(new Information(53.121487, 17.997665, "Zagrożenie życia czlowieka", "", InformationType.DANGER_LIFE, "", p3, Arrays.asList(p2)));
		mockList.add(new Information(53.124946, 18.010048, "Wypadek samochodowy", "Kolizja", InformationType.DANGER_LIFE, "", p6, Arrays.asList(p2, p3, p4)));
		mockList.add(new Information(53.132413, 17.990393, "Złamana noga", "", InformationType.DANGER_HEALTH, "", p3, Arrays.asList(p2, p7)));
		mockList.add(new Information(53.132774, 18.007817, "Skaleczenie", "", InformationType.DANGER_LIFE, "", p3, Arrays.asList(p2)));
		mockList.add(new Information(53.129787, 18.010992, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP, "", p4, Arrays.asList(p2, p3)));
		mockList.add(new Information(53.124122, 17.982840, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP, "", p3, Arrays.asList(p2, p5)));
		mockList.add(new Information(53.126182, 18.0007787, "Stłuczka", "", InformationType.OTHER, "", p3, Arrays.asList(p2)));

		mockList.add(new Information(53.126182, 18.0007787, "Jan Kolwalski", "", InformationType.MEDICAL_RESCUER, "", p1, null));
		mockList.add(new Information(53.128654, 17.988419, "Karol Kolwalski", "", InformationType.MEDICAL_RESCUER, "", p1, null));
		mockList.add(new Information(53.128654, 17.988419, "Anna Nowak", "", InformationType.RESCUER, "", p1, null));
		mockList.add(new Information(53.128654, 17.988419, "Paweł Nowak", "", InformationType.OTHER_PERSON, "", p1, null));
		mockList.add(new Information(53.126594, 17.997946, "Artur Smith", "", InformationType.OTHER_PERSON, "", p1, null));
		mockList.add(new Information(53.127006, 18.004383, "Karolina Kowalska", "", InformationType.OTHER_PERSON, "", p1, null));
		mockList.add(new Information(53.127109, 18.001637, "Kasia Pawlak", "", InformationType.OTHER_PERSON, "", p1, null));

	}

}
