package ua.com.owu.springboot.controllers;

import lombok.AllArgsConstructor;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.springboot.dao.UserDAO;
import ua.com.owu.springboot.models.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController     // анотація в Spring Framework, яка використовується для позначення класу як контролера RESTful веб-служби - використовується для обробки HTTP-запитів і надання відповідей у форматі, зазвичай, JSON або XML.
//@RequestMapping(value = "/users")  // Анотація, з якою можна скоротити запис - @GetMapping("{id}")
public class MainController {

    // url/hello [get] -> "Hello world!"
    // http://localhost:8080/hello - з postman

//    @GetMapping("/hello")    // при зверненні до цієї url має виконатися метод
//    public String hello() {
//        return "Hello world!";
//    }

    // Працюємо з класом User

    // 1 варіант/застарілий через @Autowired
    // через конструктор (@AllArgsConstructor)
    //@Autowired  // анотація в Spring Framework, яка використовується для реалізації автоматичного зв'язування залежностей.

    private UserDAO userDAO;

    @PostMapping("/users")  // зберігання юзера
    public void saveUser(@RequestBody User user) {
        // save to BD
        userDAO.save(user);
        //userDAO.delete(user);
    }

//    @GetMapping("/users")   // отримання юзерів (всіх)!
//    public List<User> getAllUsers() {
//        List<User> UsersAll = userDAO.findAll();
//        return UsersAll;
//    }

//    @GetMapping("/users/{id}")   // отримання юзера (одного)! id - довільна назва, що збираємося передавати
//    public User findUserById(@PathVariable("id") int userId) {
//    // можна public User findUserById(@PathVariable int id)
//        Optional<User> byId = userDAO.findById(userId);
//        User user = byId.get();
//        return user;
//    }

    // Update - немає, save - заміняє!

    // DELETE


    // уточнення, кастимізація запита з кодом 201.
    // 1 вар: за допомогою - @ResponseStatus
//    @ResponseStatus(HttpStatus.CREATED) //201
//    @PostMapping()  // зберігання юзерів
//    public void saveUser(@RequestBody User user) {
//        // save to BD
//        userDAO.save(user);
//        //userDAO.delete(user);
//    }

    // уточнення, кастимізація запита з кодом 201.
    // 2 вар: за допомогою
    @GetMapping("/users")   // отримання юзерів (всіх)!
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.CREATED);
    }

    // 2 вар: з headers (додатковою метаінформацією)
//    @GetMapping("/users")   // отримання юзерів (всіх)!
//    public ResponseEntity<List<User>> getAllUsers() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("customHeader", "custom value");
//        return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatus.CREATED);
//    }

    // пошук 1 юзера з використанням ResponseEntity
    @GetMapping("/users/{id}")   // отримання юзера (одного)! id - довільна назва, що збираємося передавати
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
