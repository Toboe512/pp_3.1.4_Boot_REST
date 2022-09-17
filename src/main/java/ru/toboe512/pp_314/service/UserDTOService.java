package ru.toboe512.pp_314.service;

import org.springframework.validation.BindingResult;
import ru.toboe512.pp_314.dto.UserDTO;
import ru.toboe512.pp_314.models.User;

import java.util.List;

public interface UserDTOService {

    User userDTOToUserDAO(UserDTO userDTO);
    User userDTOToUserDAO(UserDTO userDTO, BindingResult bindingResult);

    UserDTO userDAOToUserDTO(User user);

    List<UserDTO> userDAOListToUserDTOList(List<User> users);
}
