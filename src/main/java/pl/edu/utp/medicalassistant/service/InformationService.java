package pl.edu.utp.medicalassistant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.information.Information;
import pl.edu.utp.medicalassistant.model.information.InformationType;

@Service
public class InformationService {

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

		mockList.add(new Information(53.121487, 17.997665, "Zagrożenie życia czlowieka", "", InformationType.DANGER_LIFE));
		mockList.add(new Information(53.124946, 18.010048, "Wypadek samochodowy", "Kolizja", InformationType.DANGER_LIFE));
		mockList.add(new Information(53.132413, 17.990393, "Złamana noga", "", InformationType.DANGER_HEALTH));
		mockList.add(new Information(53.132774, 18.007817, "Skaleczenie", "", InformationType.DANGER_LIFE));
		mockList.add(new Information(53.129787, 18.010992, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP));
		mockList.add(new Information(53.124122, 17.982840, "Pomoc dla osoby niepełnosprawnej", "", InformationType.HELP));
		mockList.add(new Information(53.126182, 18.0007787, "Stłuczka", "", InformationType.OTHER));

		mockList.add(new Information(53.126182, 18.0007787, "Jan Kolwalski", "", InformationType.MEDICAL_RESCUER));
		mockList.add(new Information(53.128654, 17.988419, "Karol Kolwalski", "", InformationType.MEDICAL_RESCUER));
		mockList.add(new Information(53.128654, 17.988419, "Anna Nowak", "", InformationType.RESCUER));
		mockList.add(new Information(53.128654, 17.988419, "Paweł Nowak", "", InformationType.OTHER_PERSON));
		mockList.add(new Information(53.126594, 17.997946, "Artur Smith", "", InformationType.OTHER_PERSON));
		mockList.add(new Information(53.127006, 18.004383, "Karolina Kowalska", "", InformationType.OTHER_PERSON));
		mockList.add(new Information(53.127109, 18.001637, "Kasia Pawlak", "", InformationType.OTHER_PERSON));

	}

}
