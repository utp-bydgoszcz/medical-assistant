package pl.edu.utp.medicalassistant.model;

import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private double latitude;
    private double longitude;
    private double accuracy;
    private long timestamp;

    public LatLng toLatLng(){
        return new LatLng(latitude, longitude);
    }

}
