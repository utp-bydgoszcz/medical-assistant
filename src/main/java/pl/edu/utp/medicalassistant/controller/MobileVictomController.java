package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.service.MobileService;
import pl.edu.utp.medicalassistant.model.EventType;

@RestController
@RequestMapping("/call-for-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Mobile Victom Controller", description = "REST API for calling for help.", tags = {"Medical Assistant Application"})
public class MobileVictomController {

    @Autowired
    private MobileService mobileservice;

    @PostMapping("/get-information")
    public ResponseEntity getInformtion(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(mobileservice.findByUsername(auth.getName()), HttpStatus.OK); }

    @PostMapping("/need-help")
    public ResponseEntity needHelp(@RequestBody EventType eventType, @RequestBody String description) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mobileservice.needHepl(auth.getName(), eventType, description);
        return new ResponseEntity("Pomoc wezwana", HttpStatus.OK);
    }

//    @PostMapping("get-rescuers")
//    public ResponseEntity informationAboutRescuer(@RequestBody String username){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return new ResponseEntity(mobileservice.getRescuer(auth.getName()), HttpStatus.OK);
//    }
//
//    @PostMapping("cancel-rescuer")
//    public ResponseEntity cancelRescuer(@RequestBody String username){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        mobileservice.cancelRescuer(auth.getName(), username);
//    }


}
