package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.model.Event;
import pl.edu.utp.medicalassistant.model.EventStatus;
import pl.edu.utp.medicalassistant.service.EventService;
import pl.edu.utp.medicalassistant.service.MobileService;
import pl.edu.utp.medicalassistant.model.EventType;

import java.util.List;

@RestController
@RequestMapping("/call-for-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Mobile Victom Controller", description = "REST API for calling for help.", tags = {"Medical Assistant Application"})
public class MobileVictomController {

    @Autowired
    private MobileService mobileservice;

    private EventService eventService;

    @PostMapping("/get-information")
    public ResponseEntity getInformtion(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.findByUsername(auth.getName()), HttpStatus.OK); }

    @PostMapping("/need-help")
    public ResponseEntity needHelp(@RequestBody EventType eventType, @RequestBody String description) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(eventService.needHelp(auth.getName(), eventType, description), HttpStatus.OK);
    }

    @PostMapping("/get-rescuers")
    public ResponseEntity informationAboutRescuer(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.getRescuers(auth.getName()), HttpStatus.OK);
    }

    @PostMapping("/change-event-status")
    public void changeEventStatus(@RequestBody String eventId, @RequestBody EventStatus eventStatus){
        eventService.changeEventStatus(eventId,eventStatus);
    }

    @PostMapping("/change-rescuer-status")
    public void changeRescuerStatus(@RequestBody String eventId, @RequestBody String username, @RequestBody EventStatus eventStatus){
        eventService.changeRescuerStatus(eventId,username,eventStatus);
    }

    @PostMapping("/find-by-id")
    public Event findById(@RequestBody String id){
        return eventService.findById(id);
    }

    @PostMapping("/find-all")
    public List<Event> findAll(){
        return eventService.findAll();
    }

    @PostMapping("/find-available-for-user")
    public List<Event> findAvailableForUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return eventService.findAvailableForUser(auth.getName());
    }





}
