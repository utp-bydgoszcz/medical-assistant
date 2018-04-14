package pl.edu.utp.medicalassistant.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.exception.BadPasswordException;
import pl.edu.utp.medicalassistant.exception.NoUserFoundException;
import pl.edu.utp.medicalassistant.model.User;
import pl.edu.utp.medicalassistant.repository.UserRepository;


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
        userRepository.save(user);
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
