package pl.edu.utp.medicalassistant.model;

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

    @Indexed(unique=true)
    private String username;

    private String password;
    private String photoId;
    private String configuration;
    private String notification;
    private List<String> qualifications;
    private List<String> smsNumbers;
    private String smsText;
    private String patientDescription;
    private List<String> medicines;
    private String comments;
    private String rescuerDescription;
    private String phoneNumber;
    private List<String> config;
    private List<UserRole> authorites = new ArrayList<>();


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String photoId, List<String> qualifications, List<String> smsNumbers, String smsText, String patientDescription,
                List<String> medicines, String comments, String rescuerDescription, String phoneNumber, List<String> config){
        this.name = name;
        this.photoId = photoId;
        this.qualifications = qualifications;
        this.smsNumbers = smsNumbers;
        this.smsText = smsText;
        this.patientDescription = patientDescription;
        this.medicines = medicines;
        this.comments = comments;
        this.rescuerDescription = rescuerDescription;
        this.phoneNumber = phoneNumber;
        this.config = config;
    }

}
