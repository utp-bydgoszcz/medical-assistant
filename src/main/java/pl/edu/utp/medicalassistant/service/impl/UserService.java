package pl.edu.utp.medicalassistant.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.exception.BadPasswordException;
import pl.edu.utp.medicalassistant.exception.NoUserFoundException;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.model.UserConfig;
import pl.edu.utp.medicalassistant.model.UserType;
import pl.edu.utp.medicalassistant.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NoUserFoundException("Nie znaleziono użyszkodnika"));
    }

    public void createUser(User user) {

        user.setName("Cezary Kolaszewski");
        user.setType(UserType.PASSERBY);
        List<String> numbers = new ArrayList<>();
        numbers.add("442837527");
        user.setSmsNumbers(numbers);
        user.setSmsText("Jestem ranny");
        user.setPatientDescription("złamana noga");
        user.setDesees("");
        user.setMedicines("");
        user.setRescuerDescription("sztuczne oddychanie");
        user.setPhoneNumber("765433338");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());

        userRepository.save(user);
    }

    public boolean createUsers(){
        List<User> userList = new ArrayList<>();

        User user = new User("user1", "password");
        user.setName("Cezary Kolaszewski");
        user.setType(UserType.PASSERBY);
        List<String> numbers = new ArrayList<>();
        numbers.add("665433339");
        numbers.add("883777505");
        user.setSmsNumbers(numbers);
        user.setSmsText("Jestem ranny");
        user.setPatientDescription("złamana noga");
        user.setDesees("");
        user.setMedicines("");
        user.setRescuerDescription("sztuczne oddychanie");
        user.setPhoneNumber("881444053");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("Komi1", "haslo123");
        user.setName("Mikołaj Komisarek");
        user.setType(UserType.PASSERBY);
        List<String> numbers1 = new ArrayList<>();
        numbers1.add("665433339");
        numbers1.add("880225675");
        user.setSmsNumbers(numbers1);
        user.setSmsText("Miałem wypadek");
        user.setPatientDescription("złamany nos");
        user.setDesees("");
        user.setMedicines("apap");
        user.setRescuerDescription("");
        user.setPhoneNumber("883777505");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("barcwi", "pass000");
        user.setName("Bartosz Cwiklinski");
        user.setType(UserType.PASSERBY);
        List<String> numbers2 = new ArrayList<>();
        numbers2.add("881444053");
        numbers2.add("880225675");
        user.setSmsNumbers(numbers2);
        user.setSmsText("Mam zawał");
        user.setPatientDescription("zawał");
        user.setDesees("astma");
        user.setMedicines("");
        user.setRescuerDescription("");
        user.setPhoneNumber("665433339");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("tomek", "doman");
        user.setName("Tomasz Domanski");
        user.setType(UserType.RESCUER);
        List<String> numbers3 = new ArrayList<>();
        numbers3.add("881444053");
        numbers3.add("880225675");
        user.setSmsNumbers(numbers3);
        user.setSmsText("Pomocy");
        user.setPatientDescription("");
        user.setDesees("astma");
        user.setMedicines("");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("783811320");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("damian000", "haslo123");
        user.setName("Damian Ledziński");
        user.setType(UserType.RESCUER);
        List<String> numbers4 = new ArrayList<>();
        numbers4.add("881444053");
        numbers4.add("883777505");
        numbers4.add("665433339");
        numbers4.add("783811320");
        user.setSmsNumbers(numbers4);
        user.setSmsText("Potrzebna pomoc");
        user.setPatientDescription("");
        user.setDesees("");
        user.setMedicines("witamina D");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("880225675");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("alamakota", "kotamaala");
        user.setName("Ala Kot");
        user.setType(UserType.MEDICAL_RESCUER);
        List<String> numbers5 = new ArrayList<>();
        numbers5.add("881444053");
        numbers5.add("883777505");
        numbers5.add("665433339");
        numbers5.add("783811320");
        user.setSmsNumbers(numbers4);
        user.setSmsText("Potrzebna pomoc!!!AAA!!");
        user.setPatientDescription("");
        user.setDesees("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("722458976");
        user.setConfig(new UserConfig(true));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("klient", "fake");
        user.setName("Jan Nowak");
        user.setType(UserType.MEDICAL_RESCUER);
        user.setSmsNumbers(numbers4);
        user.setSmsText("Pomocy!");
        user.setPatientDescription("");
        user.setDesees("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("678345098");
        user.setConfig(new UserConfig(true));
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("cebulak", "aaa");
        user.setName("Jan Kowalski");
        user.setType(UserType.PASSERBY);
        user.setSmsNumbers(numbers4);
        user.setSmsText("Pomocy!");
        user.setPatientDescription("");
        user.setDesees("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("543799456");
        user.setConfig(new UserConfig(false));
        user.setId(user.getUsername());
        userList.add(user);

        return true;
    }

    public void deleteAll(){

        List<User> list = userRepository.findAll();

        list.forEach(us->{userRepository.delete(us); });
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoUserFoundException(("Nie znaleziono użyszkodnika")));
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new BadPasswordException("Złe hasło");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoUserFoundException("Nie znaleziono takiego użytkownika"));
    }
}
