package pl.edu.utp.medicalassistant.model;

import lombok.Getter;
import pl.edu.utp.medicalassistant.model.web.InformationType;

public enum UserType {
	MEDICAL_RESCUER("dedical-rescuer", "Ratownik medyczny"),
	RESCUER("rescuer", "Ratownik"),
	PASSERBY("other-person", "Niewykwalifikowany");

	@Getter
	private final String id;
	private final String name;

	private UserType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public InformationType toInformationType() {
		switch (this) {
			case MEDICAL_RESCUER:
				return InformationType.MEDICAL_RESCUER;
			case RESCUER:
				return InformationType.RESCUER;
			default:
				return InformationType.OTHER_PERSON;
		}
	}
}
