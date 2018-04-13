package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-aed")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Photo And AED Controller", description = "REST API for photo and aed.", tags = {"Medical Assistant Application"})
public class PhotoAndAEDController {
}
