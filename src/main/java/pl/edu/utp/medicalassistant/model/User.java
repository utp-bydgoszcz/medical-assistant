package pl.edu.utp.medicalassistant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


	@Id
	private String id;
	private String name;
	@Indexed(unique = true)
	private String username;
	private String password;
	private String photoId;
	private UserType type;
	private List<String> smsNumbers;
	private String smsText;
	private String patientDescription;
	private String diseases;
	private String medicines;
	private String rescuerDescription;
	private String phoneNumber;
	private List<UserRole> authorites = new ArrayList<>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getDescription() {
		String string = patientDescription + "\n";

		if(Optional.ofNullable(diseases).isPresent()){
			string += "choroby: " + diseases+ "\n";
		}
		if(Optional.ofNullable(medicines).isPresent()){
			string += "leki: " + medicines;
		}
		return string;
	}

}
