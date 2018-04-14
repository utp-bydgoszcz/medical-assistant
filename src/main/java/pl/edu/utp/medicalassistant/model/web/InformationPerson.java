package pl.edu.utp.medicalassistant.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationPerson {

	private String name;
	private String patientDescription;
	private String rescuerDescriptiom;
	private String photoId;
	private String phoneNumber;
	
}
