package pl.edu.utp.medicalassistant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String photoId;
    private List<String> qualifications;
    private List<String> smsNumbers;
    private String smsText;
    private String patientDescription;
    private List<String> medicines;
    private String comments;
    private String rescuerDescription;
    private String phoneNumber;
    private List<String> config;

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
