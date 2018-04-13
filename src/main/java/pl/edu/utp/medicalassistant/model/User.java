package pl.edu.utp.medicalassistant.model;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String name;
    private String photoId;
    private String configuration;
    private String notification;


}
