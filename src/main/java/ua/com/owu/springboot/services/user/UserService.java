package ua.com.owu.springboot.services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;

import java.io.File;
import java.util.List;

@Service
public interface UserService {

    // Реалізація методів знаходиться в UserServiceImplementation

    ResponseEntity<List<UserDTO>> getAllUsers();

    void saveUser(UserDTO userDTO);

    ResponseEntity<User> getUserById(int id);

    ResponseEntity<List<User>> getAllUsersAccessLevel1();

    void deleteUserById(int id);

    // lesson3 - file and mail
    void save(User user);

    void save(User user, File file);

}
