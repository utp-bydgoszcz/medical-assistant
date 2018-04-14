package pl.edu.utp.medicalassistant.model.information;

import lombok.Getter;

public enum InformationType {

	DANGER_LIFE("red-heart.png"),
	DANGER_HEALTH("orange-heart.png"),
	HELP("blue-heart.png"),
	OTHER("grey-heart.png"),
	RESCUER("rescuer.png"),
	MEDICAL_RESCUER("rescuer-medical.png"),
	OTHER_PERSON("other-person.png");
	
	@Getter
	private final String icon;

	private InformationType(String icon) {
		this.icon = icon;
	}
	
	
}
