package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passEncoder;

    public UserServiceImp(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initDB() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roles.add(adminRole);
        roles.add(userRole);

        roleDAO.saveRole(adminRole);
        roleDAO.saveRole(userRole);

        User admin = new User(
                "admin",
                "Egor",
                12,
                "admin",
                new HashSet<>()
        );

        User user = new User(
                "user",
                "Oleg",
                21,
                "user",
                new HashSet<>()
        );

        admin.getRoles().add(roleDAO.getRoleByName("ROLE_ADMIN"));
        user.getRoles().add(roleDAO.getRoleByName("ROLE_USER"));

        userDAO.save(admin);
        userDAO.save(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(passEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public List<User> read() {
        return userDAO.read();
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public User update(long id, String username, String userfirstname, int age, String password, Set<Role> role) {
        return userDAO.update(id, username, userfirstname, age, passEncoder.encode(password), role);
    }

    @Override
    public User upPage(long id) {
        return userDAO.upPage(id);
    }

}
