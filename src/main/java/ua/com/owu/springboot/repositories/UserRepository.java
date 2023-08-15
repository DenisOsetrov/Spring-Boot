package ua.com.owu.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.springboot.models.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    //  знаходження користувача за електронною поштою
    User findByEmail(String email);

}
