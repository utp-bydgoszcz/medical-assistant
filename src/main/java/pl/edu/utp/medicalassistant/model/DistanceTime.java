package pl.edu.utp.medicalassistant.model;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceTime {

    private LatLng originCords;

    private LatLng destCords;

    private String originAddress;

    private String destAddress;

    private Distance distance;

    private Duration time;


}
