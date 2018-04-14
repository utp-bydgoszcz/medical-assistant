package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.model.Aed;
import pl.edu.utp.medicalassistant.service.PhotoAndAEDService;

import java.util.List;

@RestController
@RequestMapping("/photo-aed")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Photo And Aed Controller", description = "REST API for photo and aed.", tags = {"Medical Assistant Application"})
public class PhotoAndAEDController {

    private final PhotoAndAEDService photoAndAEDService;

    @Autowired
    public PhotoAndAEDController(PhotoAndAEDService photoAndAEDService) {
        this.photoAndAEDService = photoAndAEDService;
    }

    @GetMapping("/get-photo/{name}")
    public ResponseEntity getPhoto(@PathVariable String name){
        return new ResponseEntity("", HttpStatus.OK);
    }


    @GetMapping("/get-aed")
    public ResponseEntity<List<Aed>> getAedList(){
        return new ResponseEntity(photoAndAEDService.getAedList(), HttpStatus.OK);
    }

    @PostMapping("/aed/save")
    public ResponseEntity<String> saveAedList(@RequestBody List<Aed> aeds){
        photoAndAEDService.saveAedList(aeds);
        return new ResponseEntity<>("Zapisano listę aed",HttpStatus.OK);
    }
}
