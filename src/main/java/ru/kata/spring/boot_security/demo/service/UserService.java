package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user);

    List<User> read();

    User getUserByName(String username);

    void delete(long id);

    User update(long id, String username, String userfirstname, int age, String password, Set<Role> role);

    User upPage(long id);
}
