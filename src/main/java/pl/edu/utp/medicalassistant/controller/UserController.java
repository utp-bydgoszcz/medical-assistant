package pl.edu.utp.medicalassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.service.impl.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("Użytkownik został zarejestrowany", HttpStatus.OK);
    }

    @PostMapping(value = "/user/create-all")
    public ResponseEntity<String> createAll(){
        if(userService.createUsers())
            return new ResponseEntity<>("Użytkownicy zostali zarejestrowani", HttpStatus.OK);
        else
            return new ResponseEntity<>("Wystąpił błąd", HttpStatus.OK);
    }

    @PostMapping(value = "/user/delete-all")
    public ResponseEntity<String> deleteAll(){
        userService.deleteAll();
        return new ResponseEntity<>("Użytkownicy zostali usunięci", HttpStatus.OK);
    }
}
