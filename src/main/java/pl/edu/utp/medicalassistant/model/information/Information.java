package pl.edu.utp.medicalassistant.model.information;

import java.util.List;
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
	private String address;
	private InformationPerson patient;
	private List<InformationPerson> rescuers;

	public String getIcon() {
		return informationType.getIcon();
	}

}
