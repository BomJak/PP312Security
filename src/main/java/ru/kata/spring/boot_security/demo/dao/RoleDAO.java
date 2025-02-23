package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public interface RoleDAO {

    void saveRole(Role role);

    Role getRoleByName(String roleName);

    Collection<Role> getRoleById(long id);

    Collection<Role> getAllRoles();
}
