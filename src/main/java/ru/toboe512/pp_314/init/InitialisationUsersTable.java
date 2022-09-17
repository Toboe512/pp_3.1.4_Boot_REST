package ru.toboe512.pp_314.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.toboe512.pp_314.dao.RoleDAO;
import ru.toboe512.pp_314.dao.UserDAO;
import ru.toboe512.pp_314.models.Role;
import ru.toboe512.pp_314.models.User;


import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class InitialisationUsersTable implements ApplicationListener<ApplicationReadyEvent> {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialisationUsersTable(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        roleDAO.add(new Role(1L, "ROLE_USER"));
        roleDAO.add(new Role(2L, "ROLE_ADMIN"));

        Set<Role> userRole = new HashSet<>();
        userRole.add(new Role(1L, "ROLE_USER"));

        Set<Role> adminRole = new HashSet<>();
        adminRole.add(new Role(2L, "ROLE_ADMIN"));

        Set<Role> userAdminRole = new HashSet<>();
        userAdminRole.add(new Role(1L, "ROLE_USER"));
        userAdminRole.add(new Role(2L, "ROLE_ADMIN"));

        userDAO.add(new User("User1", "User1Last", "user1@mail.ru", 20,
                passwordEncoder.encode("user"), userRole));
        userDAO.add(new User("User2", "User2Last", "user2@mail.ru", 30,
                passwordEncoder.encode("user"), userRole));
        userDAO.add(new User("Admin", "AdminLast", "admin@mail.ru", 40,
                passwordEncoder.encode("admin"), adminRole));
        userDAO.add(new User("Useradmin", "UsrerAdminLast", "useradmin@mail.ru", 50,
                passwordEncoder.encode("admin"), userAdminRole));
    }
}
