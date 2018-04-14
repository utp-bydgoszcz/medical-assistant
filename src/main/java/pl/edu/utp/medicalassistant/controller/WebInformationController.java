package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.web.Information;
import pl.edu.utp.medicalassistant.model.web.InformationType;
import pl.edu.utp.medicalassistant.service.WebInformationService;

@RestController
@RequestMapping("/web/informations")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Get Administration Controller", description = "REST API for get information.", tags = {"Medical Assistant Application"})
public class WebInformationController {

	@Autowired
	private WebInformationService webInformationService;
	
    @GetMapping("/all")
    public List<Information> getInformations(
			@RequestParam(value = "danger-live", required = false, defaultValue = "true") Boolean dangerLive,
			@RequestParam(value = "danger-health", required = false, defaultValue = "true") Boolean dangerHealth,
			@RequestParam(value = "help", required = false, defaultValue = "true") Boolean help,
			@RequestParam(value = "other", required = false, defaultValue = "true") Boolean other,
			@RequestParam(value = "rescuer", required = false, defaultValue = "true") Boolean rescuer,
			@RequestParam(value = "medical-rescuer", required = false, defaultValue = "true") Boolean medicalRescuer,
			@RequestParam(value = "other-person", required = false, defaultValue = "true") Boolean otherPerson){
		
        return webInformationService.getInformations()
				.stream()
				.filter(i -> i.getInformationType() != InformationType.DANGER_HEALTH || dangerLive)
				.filter(i -> i.getInformationType() != InformationType.DANGER_LIFE || dangerHealth)
				.filter(i -> i.getInformationType() != InformationType.HELP || help)
				.filter(i -> i.getInformationType() != InformationType.OTHER || other)
				.filter(i -> i.getInformationType() != InformationType.RESCUER || rescuer)
				.filter(i -> i.getInformationType() != InformationType.MEDICAL_RESCUER || medicalRescuer)
				.filter(i -> i.getInformationType() != InformationType.OTHER_PERSON || otherPerson)
				.collect(Collectors.toList());
    }
	
	
}
