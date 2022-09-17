package ru.toboe512.pp_314.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.toboe512.pp_314.dto.UserDTO;
import ru.toboe512.pp_314.models.User;
import ru.toboe512.pp_314.service.UserDTOService;


@RestController
@RequestMapping("/api/user")
public class UserRESTController {

    private final UserDTOService userDTOService;

    @Autowired
    public UserRESTController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping
    public UserDTO getAuthenticationUser(@AuthenticationPrincipal User authenticationUser) {
        return userDTOService.userDAOToUserDTO(authenticationUser);
    }
}
