package pl.edu.utp.medicalassistant.model;

import lombok.Getter;
import pl.edu.utp.medicalassistant.model.web.InformationType;

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
	
	public InformationType toInformationType() {
		switch (this) {
			case DANGER_LIVE:
				return InformationType.DANGER_LIFE;
			case DANGER_HEALTH:
				return InformationType.DANGER_HEALTH;
			case HELP:
				return InformationType.HELP;
			default:
				return InformationType.OTHER;
		}
	}
	
}
