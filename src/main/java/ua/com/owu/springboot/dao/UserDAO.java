package ua.com.owu.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.springboot.models.User;

public interface UserDAO extends JpaRepository<User, Integer>{
    // зробили вже всі CRUD щперації


}
