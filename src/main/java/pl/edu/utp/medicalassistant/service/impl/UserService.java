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
import java.util.stream.Collectors;


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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        user.setSmsText("Cezary");
        user.setPatientDescription("Niepełnosprawny");
        user.setDiseases("");
        user.setMedicines("");
        user.setRescuerDescription("Harcerz");
        user.setPhoneNumber("881444053");
        user.setPhotoId(user.getUsername());
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user2", "password");
        user.setName("Mikołaj Komisarek");
        user.setType(UserType.PASSERBY);
        List<String> numbers1 = new ArrayList<>();
        numbers1.add("665433339");
        numbers1.add("880225675");
        user.setSmsNumbers(numbers1);
        user.setSmsText("Mikołąj");
        user.setPatientDescription("Niepełnosprawny");
        user.setDiseases("");
        user.setMedicines("apap");
        user.setRescuerDescription("Harcerz");
        user.setPhoneNumber("883777505");
        user.setPhotoId(user.getUsername());
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user3", "password");
        user.setName("Bartosz Cwiklinski");
        user.setType(UserType.PASSERBY);
        List<String> numbers2 = new ArrayList<>();
        numbers2.add("881444053");
        numbers2.add("880225675");
        user.setSmsNumbers(numbers2);
        user.setSmsText("Bartosz");
        user.setPatientDescription("Niepełnosprawny");
        user.setDiseases("astma");
        user.setMedicines("");
        user.setRescuerDescription("Ratownik medyczny");
        user.setPhoneNumber("665433339");
        user.setPhotoId(user.getUsername());
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user4", "password");
        user.setName("Tomasz Domanski");
        user.setType(UserType.RESCUER);
        List<String> numbers3 = new ArrayList<>();
        numbers3.add("881444053");
        numbers3.add("880225675");
        user.setSmsNumbers(numbers3);
        user.setSmsText("Tomasz");
        user.setPatientDescription("");
        user.setDiseases("astma");
        user.setMedicines("");
        user.setRescuerDescription("Chętnie pomagam");
        user.setPhoneNumber("783811320");
        user.setPhotoId(user.getUsername());
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user5", "password");
        user.setName("Damian Ledziński");
        user.setType(UserType.RESCUER);
        List<String> numbers4 = new ArrayList<>();
        numbers4.add("881444053");
        numbers4.add("883777505");
        numbers4.add("665433339");
        numbers4.add("783811320");
        user.setSmsNumbers(numbers4);
        user.setSmsText("Damian");
        user.setPatientDescription("");
        user.setDiseases("");
        user.setMedicines("witamina D, leki przeciwzakrzepowe");
        user.setRescuerDescription("Chętnie pomagam");
        user.setPhoneNumber("880225675");
        user.setPhotoId(user.getUsername());
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user6", "password");
        user.setName("Ala Kot");
        user.setType(UserType.MEDICAL_RESCUER);
        List<String> numbers5 = new ArrayList<>();
        numbers5.add("881444053");
        numbers5.add("883777505");
        numbers5.add("665433339");
        numbers5.add("783811320");
        user.setSmsNumbers(numbers4);
        user.setSmsText("Ala");
        user.setPatientDescription("");
        user.setDiseases("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("wykwalifikowana pomoc medyczna");
        user.setPhoneNumber("722458976");
        user.setPhotoId("none");
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user7", "password");
        user.setName("Jan Nowak");
        user.setType(UserType.MEDICAL_RESCUER);
        user.setSmsNumbers(numbers4);
        user.setSmsText("Jan");
        user.setPatientDescription("");
        user.setDiseases("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("wykwalifikowana pomoc medyczna");
        user.setPhoneNumber("678345098");
        user.setPhotoId("none");
        user.setId(user.getUsername());
        userList.add(user);

        user = new User("user8", "password");
        user.setName("Jan Kowalski");
        user.setType(UserType.PASSERBY);
        user.setSmsNumbers(numbers4);
        user.setSmsText("Jan");
        user.setPatientDescription("");
        user.setDiseases("");
        user.setMedicines("leki przeciwzakrzepowe");
        user.setRescuerDescription("resuscytacja");
        user.setPhoneNumber("543799456");
        user.setPhotoId("none");
        user.setId(user.getUsername());
        userList.add(user);
        userList = userList.stream().peek(u -> u.setPassword(passwordEncoder.encode(u.getPassword()))).collect(Collectors.toList());

        userList.forEach(user1 -> userRepository.save(user1));
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
