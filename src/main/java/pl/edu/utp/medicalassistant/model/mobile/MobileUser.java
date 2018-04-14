package pl.edu.utp.medicalassistant.model.mobile;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.model.UserType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileUser {

	private String username;
	private String name;
	private String photoId;
	private UserType type;
	private List<String> smsNumbers;
	private String patientDescription;
	private String rescuerDescription;
	private String phoneNumber;

	public MobileUser(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.photoId = user.getPhotoId();
		this.type = user.getType();
		this.smsNumbers = user.getSmsNumbers();
		this.patientDescription = user.getDescription();
		this.rescuerDescription = user.getRescuerDescription();
		this.phoneNumber = user.getPhoneNumber();
	}
	
	
	
}
