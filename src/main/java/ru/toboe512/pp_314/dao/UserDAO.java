package ru.toboe512.pp_314.dao;

import ru.toboe512.pp_314.models.Role;
import ru.toboe512.pp_314.models.User;

import java.util.List;

public interface UserDAO {
    void add(User user);

    List<User> listUsers();

    void delete(User user);

    void update(User user);

    User getUser(Long id);

    User getUserByEmail(String email);

    Role getRoleByUser(User user);
}
