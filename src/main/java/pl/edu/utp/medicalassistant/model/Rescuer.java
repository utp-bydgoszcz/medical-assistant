package pl.edu.utp.medicalassistant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Rescuer {

    private String rescuerId;
    private Date date;
    private RescuerStatus status;

}
