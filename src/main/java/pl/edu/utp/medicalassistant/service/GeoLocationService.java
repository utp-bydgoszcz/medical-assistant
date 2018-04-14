package pl.edu.utp.medicalassistant.service;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.DistanceTime;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.google.maps.model.AddressType.STREET_ADDRESS;

@Service
public class GeoLocationService {

    private HashMap<LatLng, Object> addressCache = new HashMap<>();

    @Value("${google.maps.distance.matrix}")
    private String DISTANCE_MATRIX_API;

    @Value("${google.maps.geocoding}")
    private String GEOCODING_API;

    private String PL = "pl-PL";

    private GeoApiContext distanceContext;

    private GeoApiContext geocodingContext;

    @PostConstruct
    private void buildClients() {
        distanceContext = new GeoApiContext.Builder().apiKey(DISTANCE_MATRIX_API).build();
        geocodingContext = new GeoApiContext.Builder().apiKey(GEOCODING_API).build();
    }


    public List<DistanceTime> getDistanceTime(LatLng origin, LatLng dest) throws InterruptedException, ApiException, IOException {
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distanceContext);
        DistanceMatrix trix = req.origins(origin)
                .destinations(dest)
                .mode(TravelMode.WALKING)
                .language(PL)
                .await();

        return extractDistanceTimes(origin, dest, trix);
    }

    public String getAddressFromCords(LatLng cords) throws InterruptedException, ApiException, IOException {
        Optional<LatLng> latLngOptional = isInsideCache(cords,addressCache);

        if(latLngOptional.isPresent()){
            return addressCache.get(latLngOptional.get()).toString();
        }

        GeocodingApiRequest req = GeocodingApi.newRequest(geocodingContext);

        GeocodingResult[] results = req.latlng(cords).resultType(STREET_ADDRESS).await();
        if (results.length > 0) {
            addressCache.put(cords,results[0].formattedAddress);
            return results[0].formattedAddress;
        }
        return "Nie znaleziono adresu.";
    }


    private Optional<LatLng> isInsideCache(LatLng cords,HashMap<LatLng,Object> map) {
        return map.keySet().parallelStream().filter(crd -> {
                Boolean lat = Math.abs(crd.lat - cords.lat) < 0.0001D;
                Boolean lon = Math.abs(crd.lng - cords.lng) < 0.0001D;
                return lat && lon;
            }).findFirst();
    }


    private List<DistanceTime> extractDistanceTimes(LatLng origin, LatLng dest, DistanceMatrix trix) {
        List<DistanceTime> distanceTimes = new ArrayList<>();
        for (int x = 0; x < trix.rows.length; x++) {
            String originStr = trix.originAddresses[x];
            String destStr = trix.destinationAddresses[x];
            Distance distance = trix.rows[x].elements[x].distance;
            Duration duration = trix.rows[x].elements[x].duration;
            distanceTimes.add(new DistanceTime(origin, dest, originStr, destStr, distance, duration));
        }
        return distanceTimes;
    }


}
