package pl.edu.utp.medicalassistant.controller;

import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.repository.UserRepository;

@RestController
@RequestMapping("/test")
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@Api(value = "Test controllerr", description = "REST API for get information.", tags = {"Medical Assistant Application"})
public class TestController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	} 
	
}
