package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserDAO userDAO;

    public UserDetailsServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("ERROR");
        }
        user.getAuthorities();
        return user;
    }
}
