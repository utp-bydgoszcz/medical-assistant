package pl.edu.utp.medicalassistant.controller;


import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.Cords;
import pl.edu.utp.medicalassistant.service.GeoLocationService;

import java.io.IOException;


@RestController
@RequestMapping(value = "/location")
public class GoogleMapsController {

    private final GeoLocationService geoLocationService;

    @Autowired
    public GoogleMapsController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @PostMapping(value = "/distance-time")
    public ResponseEntity<Object> getDistanceTime(@RequestBody Cords cords) throws InterruptedException, ApiException, IOException {
        return new ResponseEntity<>(geoLocationService.getDistanceTime(
                cords.getOrigin(),
                cords.getDest()),HttpStatus.OK);
    }

    @PostMapping(value = "/address")
    public ResponseEntity<Object> getAddressFromCords(@RequestBody LatLng cords) throws InterruptedException, ApiException, IOException {
        return new ResponseEntity<>(geoLocationService.getAddressFromCords(
                cords),HttpStatus.OK);
    }
}
