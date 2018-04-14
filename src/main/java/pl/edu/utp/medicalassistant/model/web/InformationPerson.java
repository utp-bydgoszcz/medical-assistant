package pl.edu.utp.medicalassistant.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.utp.medicalassistant.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationPerson {

	private String name;
	private String patientDescription;
	private String rescuerDescriptiom;
	private String photoId;
	private String phoneNumber;

	public InformationPerson(User user) {
		this.name = user.getName();
		this.patientDescription = user.getDescription();
		this.rescuerDescriptiom = user.getRescuerDescription();
		this.photoId = user.getUsername();
		this.phoneNumber = user.getPhoneNumber();
	}
	
	
	
}
