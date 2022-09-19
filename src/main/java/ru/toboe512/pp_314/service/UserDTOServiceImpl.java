package ru.toboe512.pp_314.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.toboe512.pp_314.dao.RoleDAO;
import ru.toboe512.pp_314.dto.UserDTO;
import ru.toboe512.pp_314.models.Role;
import ru.toboe512.pp_314.models.User;
import ru.toboe512.pp_314.utils.UserNotCreatedException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDTOServiceImpl implements UserDTOService{

    public final RoleDAO roleDAO;

    @Autowired
    public UserDTOServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public User userDTOToUserDAO(UserDTO userDTO) {
        Set<Role> roleSet = new HashSet<>();

        for (String role : userDTO.getRoles()) {
            roleSet.add(roleDAO.findRoleById(Long.valueOf(role)));
        }

        return new User(userDTO.getId(), userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getAge(),
                userDTO.getPassword(),
                roleSet);
    }

    @Override
    public User userDTOToUserDAO(UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(bindingResult.getFieldErrors());
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
        return userDTOToUserDAO(userDTO);
    }

    @Override
    public UserDTO userDAOToUserDTO(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastname(),
                user.getEmail(),
                user.getAge(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(Role::getRole)
                        .map(x -> x.replace("ROLE_", ""))
                        .toArray(String[]::new));
    }

    @Override
    public List<UserDTO> userDAOListToUserDTOList(List<User> users) {
        return users.stream().map(this::userDAOToUserDTO).collect(Collectors.toList());
    }
}
