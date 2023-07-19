package ua.com.owu.springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;
import ua.com.owu.springboot.models.views.Views;
import ua.com.owu.springboot.services.UserService;

import java.util.List;


//@AllArgsConstructor   // Через Lombok @Qualifier не працює, тому видаляємо @AllArgsConstructor і створюємо конструктор самостійно + міняємо форму запису @Qualifier("one")
@RestController             // позначення класу як контролера
@RequestMapping(value = "/users")

public class UserController {

    //@Qualifier("one")
    private UserService userService;

    // конструктор
    public UserController(@Qualifier("one") UserService userService) {
        this.userService = userService;
    }


    @PostMapping()        // post з валідацією DIO (одне поле name)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody UserDTO userDTO) {  // @Valid приберемо, бо не прописані умови валідації
        userService.saveUser(userDTO);
    }


    @GetMapping()   // All users!
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")   // отримання юзера (одного)! id - довільна назва, що збираємося передавати
    public ResponseEntity<User> findUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }


    @GetMapping("/level1")       // Level 1 - буде з id, name
    @JsonView(Views.Level1.class)  // (Views.Level2.class) - виводитиме лише name
    public ResponseEntity<List<User>> getAllUsersAccessLevel1() {
        return userService.getAllUsersAccessLevel1();
    }


    @DeleteMapping("/{id}") // Удаляємо користувача за його ID
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted.");
    }

}

