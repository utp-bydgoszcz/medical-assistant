package pl.edu.utp.medicalassistant.model;

import lombok.Getter;

public enum EventType {

	DANGER_LIVE("danger-live", "Zagrożenie życia"),
	DANGER_HEALTH("danger-health", "Zagrożenie zdrowia"),
	HELP("help", "Potrzebna pomoc"),
	OTHER("other", "Inne");

	@Getter
	private final String id;
	private final String name;

	private EventType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
