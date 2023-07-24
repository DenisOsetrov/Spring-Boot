package ua.com.owu.springboot.services.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.springboot.dao.UserDAO;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("two")             // two - довільна назва 2-ї реалізації методів UserService
@AllArgsConstructor
public class UserServiceImplementation2 implements UserService {

    private UserDAO userDAO;

    // Метод для отримання всіх користувачів
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userDAO.findAll().stream()
                .map(user -> new UserDTO(user.getName()))
                .collect(Collectors.toList());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("custom Header", "custom value");

        return new ResponseEntity<>(userDTOs, httpHeaders, HttpStatus.CREATED);
    }

    // Метод для збереження користувача
    public void saveUser(UserDTO userDTO) {
        String name = userDTO.getUsername();
        User user = new User(name);

        // Приклад валідації
        if (user.getId() < 0) {
            throw new RuntimeException("id cannot be less than 0!");
        }

        userDAO.save(user);
    }

    // Метод для отримання користувача за ідентифікатором
    public ResponseEntity<User> getUserById(int id) {
        if (id < 0) {
            throw new RuntimeException("Invalid ID!");
        }

        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    // Метод для отримання всіх користувачів з рівнем доступу 1
    public ResponseEntity<List<User>> getAllUsersAccessLevel1() {
        List<User> users = userDAO.findAll();
        return ResponseEntity.ok(users);
    }

    // Метод для видалення користувача за ідентифікатором
    public void deleteUserById(int id) {
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
        userDAO.deleteById(id);
    }

    @Override    // Ctrl + I - імплементація методів
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void save(User user, File file) {

    }
}
