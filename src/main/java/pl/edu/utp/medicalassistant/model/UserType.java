package pl.edu.utp.medicalassistant.model;

import lombok.Getter;

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
}
