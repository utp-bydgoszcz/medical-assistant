package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.medicalassistant.model.Location;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.service.PositionService;


@RestController
@RequestMapping("/position")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Position Controller", description = "REST API for position information.", tags = {"Medical Assistant Application"})
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/set-position")
    public void setPosition(@RequestBody Location location){

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        positionService.setLocation(location, user.getUsername());
    }

}
