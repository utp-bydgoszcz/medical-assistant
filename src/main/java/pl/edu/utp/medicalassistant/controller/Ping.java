package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Ping controller", description = "REST API for get information.", tags = {"Medical Assistant Application"})
public class Ping {

	@GetMapping("")
    public String ping(){
        return "pong";
    }
	
}
