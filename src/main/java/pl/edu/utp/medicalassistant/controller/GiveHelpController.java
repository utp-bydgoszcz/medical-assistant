package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/give-help")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Give Help Controller", description = "REST API for giving help.", tags = {"Medical Assistant Application"})
public class GiveHelpController {
}
