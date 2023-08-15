package ua.com.owu.springboot.services.user;

import ua.com.owu.springboot.models.user.User;

public interface UserService {

    void registerUser(User user);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    // буде відповідати за перевірку користувача за електронною поштою та паролем
    User authenticateUser(String email, String password);
}
