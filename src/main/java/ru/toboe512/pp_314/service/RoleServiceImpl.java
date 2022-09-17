package ru.toboe512.pp_314.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.toboe512.pp_314.dao.RoleDAO;
import ru.toboe512.pp_314.models.Role;
import ru.toboe512.pp_314.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public User setRolesByStrings(String[] roles, User user) {
        if (roles != null) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                roleSet.add(roleDAO.findRoleById(Long.valueOf(role)));
            }
            user.setRoles(roleSet);
        }
        return user;
    }

    @Transactional
    @Override
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    public List<Role> listRoless() {
        return roleDAO.listRoles();
    }

    @Transactional
    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }
}
