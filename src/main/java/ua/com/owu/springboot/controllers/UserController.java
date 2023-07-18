package ua.com.owu.springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot.dao.UserDAO;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;
import ua.com.owu.springboot.models.views.Views;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController             // позначення класу як контролера
@RequestMapping(value = "/users")

public class UserController {

    private UserDAO userDAO;

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveUser(@RequestBody @Valid User user) {
//        userDAO.save(user);
//    }



    @PostMapping()        // post з валідацією DIO (одне поле name)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User userDTO) {  // @Valid приберемо, бо не прописані умови валідації
        String name = userDTO.getName();
        User user = new User(name);     // зробити конструктор з одним полем в User
        userDAO.save(user);
    }


    // уточнення, кастимізація запита з кодом 201.
    // 1 вар: за допомогою - @ResponseStatus
//    @ResponseStatus(HttpStatus.CREATED) //201
//    @PostMapping()  // зберігання юзерів
//    public void saveUser(@RequestBody User user) {
//        // save to BD
//        userDAO.save(user);
//        //userDAO.delete(user);
//    }



    // уточнення, кастимізація запита з кодом 201 та з хедерами!
    @GetMapping()   // All users!
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("custom Header", "custom value");
        List<UserDTO> collect = userDAO.findAll().stream().map(user -> new UserDTO(user.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.CREATED);
    }

//    @GetMapping("/users")   // All users!
//    public List<User> getAllUsers() {
//        List<User> UsersAll = userDAO.findAll();
//        return UsersAll;
//    }


    // запит з отриманням дозованої інформації level 1, 2, 3...
    // створюємо package views в models, після цього робимо зміни в User
    @GetMapping("/level1")       // Level 1 - буде з id, name
    @JsonView(Views.Level1.class)  // (Views.Level2.class) - виводитиме лише name
    public List<User> getAllUsersAccessLevel1() {
        return userDAO.findAll();
    }



//    @GetMapping("/users/{id}")   // отримання юзера (одного)! id - довільна назва, що збираємося передавати
//    public User findUserById(@PathVariable("id") int userId) {
//    // можна public User findUserById(@PathVariable int id)
//        Optional<User> byId = userDAO.findById(userId);
//        User user = byId.get();
//        return user;
//    }

    // пошук 1 юзера з використанням ResponseEntity
    @GetMapping("/{id}")   // отримання юзера (одного)! id - довільна назва, що збираємося передавати
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        Optional<User> byId = userDAO.findById(id);
        if (byId.isPresent()) {                     // перевірка на id, або 404!
            User user = byId.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

