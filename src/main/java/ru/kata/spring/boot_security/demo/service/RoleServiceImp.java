package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class RoleServiceImp implements RoleService {
    private RoleDAO roleDAO;

    public RoleServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDAO.getRoleByName(roleName);
    }

    @Override
    public Collection<Role> getRoleById(long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public Collection<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
