package pl.edu.utp.medicalassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

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
	@JsonIgnore
	private String patientDescription;
	@JsonIgnore
	private String desees;
	@JsonIgnore
	private String medicines;
	private String rescuerDescription;
	private String phoneNumber;
	private UserConfig config;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getDescription() {
		return String.format("%s\nchoroby: %s\nprzyjmuje leki:%s", patientDescription, desees, medicines);
	}
}
