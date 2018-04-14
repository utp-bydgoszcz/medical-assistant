package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.User;

@RestController
@RequestMapping("/give-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Get Information Controller", description = "REST API for get information.", tags = {"Medical Assistant Application"})
public class GetInformationController {
	
    @GetMapping("/get-configuration")
    public ResponseEntity getConfiguration(){
        return new ResponseEntity("", HttpStatus.OK);
    }

    @GetMapping("/get-events")
    public ResponseEntity getEvents(){
        return new ResponseEntity("", HttpStatus.OK);
    }
}
