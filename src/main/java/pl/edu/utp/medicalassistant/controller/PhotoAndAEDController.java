package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo-aed")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Photo And AED Controller", description = "REST API for photo and aed.", tags = {"Medical Assistant Application"})
public class PhotoAndAEDController {

    @GetMapping("/get-photo/{name}")
    public ResponseEntity getPhoto(@PathVariable String name){
        return new ResponseEntity("", HttpStatus.OK);
    }

    @GetMapping("/get-aed")
    public ResponseEntity getAedList(){
        return new ResponseEntity("", HttpStatus.OK);
    }
}
