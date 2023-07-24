package ua.com.owu.springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.springboot.models.User;
import ua.com.owu.springboot.models.dto.UserDTO;
import ua.com.owu.springboot.models.views.Views;
import ua.com.owu.springboot.services.user.UserService;

import java.io.File;
import java.util.List;


//@AllArgsConstructor   // Через Lombok @Qualifier не працює, тому видаляємо @AllArgsConstructor і створюємо конструктор самостійно + міняємо форму запису @Qualifier("one")
@RestController             // позначення класу як контролера
@RequestMapping(value = "/users")

public class UserController {

    //@Qualifier("one")
    private final UserService userService;

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

    // lessons 3 only file save
//    @SneakyThrows
//    @PostMapping("/savewithavatar")
//    public void saveWithAvatar(@RequestParam("name") String name, @RequestParam("avatar") MultipartFile avatar) {  // Значенння "avatar" і "name" - мають бути такими, як і в Постмені!
//        User user = new User();
//        user.setName(name);
//        String originalFilename = avatar.getOriginalFilename(); // витягуємо ім'я файлу, а балі його зберігаємо
//        user.setAvatar(originalFilename);  // {name:Kokos, avatar: Foto-avatar-lesson3.jpg}
//
//        String path = System.getProperty("user.home") + File.separator + "MySaveToPrograming" + File.separator + originalFilename;  // куди зберігати створене!
//        File transferDestinationFile = new File(path);
//        avatar.transferTo(transferDestinationFile);
//        userService.save(user);
//    }


    // lessons 3 email+file
    @SneakyThrows
    @PostMapping("/savewithavatar")
    public void saveWithAvatar(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("avatar") MultipartFile avatar
    ) {
        User user = new User();
        user.setName(name);
        user.setEmail(email); // Додаємо параметр email до юзера
        String originalFilename = avatar.getOriginalFilename();
        user.setAvatar(originalFilename);

        String path = System.getProperty("user.home") + File.separator + "MySaveToPrograming" + File.separator + originalFilename;
        File transferDestinationFile = new File(path);
        avatar.transferTo(transferDestinationFile);
        userService.save(user, transferDestinationFile);
    }




    // lessons 3 email (треба логіку перекинути в Service)
    @GetMapping("/activate/{id}")   // з MailService
    public void activateUser (@PathVariable int id) {
        ResponseEntity<User> userById = userService.getUserById(id);
        User user = userById.getBody();
        user.setActivated(true);       // Activated - поле у Юзера/ змінюємо на протилежне
        userService.save(user);        // буде update user
    }

    @PostMapping("/saveWithEmail")
    public void saveWithEmail(@RequestBody User user) {
        userService.save(user);
    }

}
























