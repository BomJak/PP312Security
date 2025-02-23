package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void save(User user) {
        if (user != null) {
            String encodePass = passwordEncoder().encode(user.getPassword());
            user.setPassword(encodePass);
            entityManager.persist(user);
        }
    }

    @Override
    public User getUserByName(String username) {
        List<User> query = entityManager.createQuery("SELECT u FROM User u where u.username = :userName", User.class)
                .setParameter("userName", username).getResultList();
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    @Override
    public List<User> read() {
        return entityManager.createQuery("from User ").getResultList();
    }

    @Override
    @Transactional
    public User update(long id, String username, String userFirstname, int age, String password,
                       Set<Role> role) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setUsername(username);
            user.setUserFirstname(userFirstname);
            user.setAge(age);
            user.setPassword(passwordEncoder().encode(password));
            user.setRoles(role);
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    public User upPage(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        entityManager.createQuery("delete from User u where u.id = :userId")
                .setParameter("userId", id)
                .executeUpdate();
    }
}
