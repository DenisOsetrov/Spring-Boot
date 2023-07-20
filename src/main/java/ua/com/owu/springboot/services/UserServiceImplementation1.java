package ua.com.owu.springboot.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.dao.UserDAO;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("one")             // one - довільна назва 1-ї реалізації методів UserService
@AllArgsConstructor
// UserServiceImplementation1 - це 1-й спосіб реалізації UserService
public class UserServiceImplementation1 implements UserService {

    // Назва методів без реалізації знаходиться в UserService
    private UserDAO userDAO;

    // створимо методи, які будуть з UserController забирати логіку

    public ResponseEntity<List<UserDTO>> getAllUsers() {   // get all users
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("custom Header", "custom value");
        List<UserDTO> collect = userDAO.findAll().stream().map(user -> new UserDTO(user.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.CREATED);
    }


    public void saveUser(UserDTO userDTO) {              // post user
        String name = userDTO.getUsername();
        User user = new User(name);
        // save with validation
        if (user.getId() < 0) {
            throw new RuntimeException("id !!!!<0!");
        }
        userDAO.save(user);
    }


    public ResponseEntity<User> getUserById(int id) {  // user by id
        if (id < 0) {
            throw new RuntimeException();
        }
        Optional<User> byId = userDAO.findById(id);
        User user = byId.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<List<User>> getAllUsersAccessLevel1() {  // all users level1
        List<User> users = userDAO.findAll(); // Отримуємо всіх користувачів з бази даних
        return ResponseEntity.ok(users); // Повертаємо список користувачів зі статусом 200 OK
    }


    public void deleteUserById(int id) {
        // Перевіряємо, чи користувач з таким ID існує у базі даних
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
        // Видаляємо користувача
        userDAO.deleteById(id);
    }

    // lesson3
    @Override    // Ctrl + I - імплементація методів
    public void save(User user) {
        userDAO.save(user);
    }
}
