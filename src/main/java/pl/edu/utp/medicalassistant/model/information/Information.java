package pl.edu.utp.medicalassistant.model.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {

	private double lat;
	private double lng;
	private String title;
	private String description;
	private InformationType informationType;

	public String getIcon() {
		return informationType.getIcon();
	}

}
