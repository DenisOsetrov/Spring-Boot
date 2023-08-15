package ua.com.owu.springboot.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.owu.springboot.models.user.User;
import ua.com.owu.springboot.services.user.UserService;

@Controller
@AllArgsConstructor

// контролер для обробки входу користувача
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard"; // або будь-яка інша сторінка після входу
        } else {
            // Обробка помилки авторизації
            return "login"; // повернення на сторінку входу з повідомленням про помилку
        }
    }

}
