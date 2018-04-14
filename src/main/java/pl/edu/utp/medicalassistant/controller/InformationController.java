package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.information.Information;
import pl.edu.utp.medicalassistant.service.InformationService;

@RestController
@RequestMapping("/web/informations")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Get Administration Controller", description = "REST API for get information.", tags = {"Medical Assistant Application"})
public class InformationController {

	@Autowired
	private InformationService informationService;
	
    @GetMapping("/all")
    public List<Information> getInformations(){
        return informationService.getInformations();
    }
}
