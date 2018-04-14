package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.service.Mobileservice;

@RestController
@RequestMapping("/call-for-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Mobile Victom Controller", description = "REST API for calling for help.", tags = {"Medical Assistant Application"})
public class MobileVictomController {

    @Autowired
    private Mobileservice mobileservice;

    @PostMapping("/get-information")
    public ResponseEntity getInformtion(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.findByUsername(auth.getName()), HttpStatus.OK); }

//    @PostMapping("/call-for-help")
//    public ResponseEntity callForHelp(@RequestBody EventType eventType, ) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //auth.getName()
//    }


}
