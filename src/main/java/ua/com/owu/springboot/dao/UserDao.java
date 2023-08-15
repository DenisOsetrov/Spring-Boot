package ua.com.owu.springboot.dao;

import ua.com.owu.springboot.models.user.User;

public interface UserDao {

    User save(User user);

    User findById(int id);

    User findByEmail(String email);
}
