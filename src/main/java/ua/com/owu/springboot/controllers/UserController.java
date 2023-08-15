package ua.com.owu.springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.owu.springboot.models.user.User;
import ua.com.owu.springboot.services.user.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistrationForm(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}