package ua.com.owu.springboot.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;

import java.util.List;

@Service
public interface UserService {

    // Реалізація методів знаходиться в UserServiceImplementation

    ResponseEntity<List<UserDTO>> getAllUsers();

    void saveUser(UserDTO userDTO);

    ResponseEntity<User> getUserById(int id);

    ResponseEntity<List<User>> getAllUsersAccessLevel1();

    void deleteUserById(int id);

}