package ru.toboe512.pp_314.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.toboe512.pp_314.dao.UserDAO;
import ru.toboe512.pp_314.models.User;


@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailService {
    private final UserDAO userDAO;

    @Autowired
    public UserDetailServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User name not found!");
        }
        return user;
    }
}
