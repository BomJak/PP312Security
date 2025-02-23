package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void save(User user);

    List<User> read();

    User update(long id, String username, String userfirstname, int age, String password, Set<Role> role);

    void delete(long id);

    User getUserByName(String username);

    User upPage(long id);
}
