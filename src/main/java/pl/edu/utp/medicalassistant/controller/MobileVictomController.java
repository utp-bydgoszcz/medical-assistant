package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.exception.CreateMobileEventException;
import pl.edu.utp.medicalassistant.model.*;
import pl.edu.utp.medicalassistant.model.mobile.MobileEvent;
import pl.edu.utp.medicalassistant.model.mobile.MobileUser;
import pl.edu.utp.medicalassistant.repository.UserRepository;
import pl.edu.utp.medicalassistant.service.EventService;
import pl.edu.utp.medicalassistant.service.GeoLocationService;
import pl.edu.utp.medicalassistant.service.MobileService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/call-for-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Mobile Victom Controller", description = "REST API for calling for help.", tags = {"Medical Assistant Application"})
public class MobileVictomController {

    @Autowired
    private MobileService mobileservice;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private GeoLocationService geoLocationService;

    @PostMapping("/get-information")
    public ResponseEntity getInformtion() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.findByUsername(auth.getName()), HttpStatus.OK);
    }

    @PostMapping("/need-help/{eventType}")
    public ResponseEntity needHelp(@RequestBody HashMap<String, Object> body, @PathVariable String eventType) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(eventService.needHelp(auth.getName(), EventType.valueOf(eventType), (String) body.get("description")), HttpStatus.OK);
    }

    @PostMapping("/get-rescuers")
    public ResponseEntity informationAboutRescuer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.getRescuers(auth.getName()), HttpStatus.OK);
    }

    @PostMapping("/change-event-status/{eventStatus}")
    public void changeEventStatus(@RequestBody String eventId, @PathVariable String eventStatus) {
        eventService.changeEventStatus(eventId, EventStatus.valueOf(eventStatus));
    }

    @PostMapping("/change-rescuer-status/{rescuerStatus}")
    public void changeRescuerStatus(@RequestBody HashMap<String,String> map , @PathVariable String rescuerStatus) {

        eventService.changeRescuerStatus(map.get("eventId"), map.get("rescuerName"), RescuerStatus.valueOf(rescuerStatus));
    }

    @PostMapping("/find-by-id")
    public MobileEvent findById(@RequestBody String id) {
        return creatMobileEvent(eventService.findById(id));
    }

    @PostMapping("/find-all")
    public List<MobileEvent> findAll() {

        List<MobileEvent> mobileEvents = new ArrayList<>();

        eventService.findAll().forEach(event -> mobileEvents.add(creatMobileEvent(event)));

        return mobileEvents;
    }

    @PostMapping("/find-available-for-user")
    public List<MobileEvent> findAvailableForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<MobileEvent> mobileEvents = new ArrayList<>();

        eventService.findAvailableForUser(auth.getName()).forEach(event -> mobileEvents.add(creatMobileEvent(event)));

        return mobileEvents;

    }

    private MobileEvent creatMobileEvent(Event event) {
        String address = null;
        try {
            address = geoLocationService.getAddressFromCords(event.toLatLng());
        } catch (Exception e) {
            throw new CreateMobileEventException("Błąd przy pobieraniu lokalizajci.");
        }

        return new MobileEvent(event, getNameFromUsername(event.getUserId()), address, getMobileUser(event.getUserId()));

    }

    private String getNameFromUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        return user.get().getName();
    }

    private MobileUser getMobileUser(String username) {
        Optional<User> user = repository.findByUsername(username);
        return new MobileUser(user.get());
    }

}
