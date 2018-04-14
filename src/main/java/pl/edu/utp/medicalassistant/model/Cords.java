package pl.edu.utp.medicalassistant.model;

import com.google.maps.model.LatLng;
import lombok.Data;

@Data
public class Cords {

    private LatLng origin;
    private LatLng dest;
}
