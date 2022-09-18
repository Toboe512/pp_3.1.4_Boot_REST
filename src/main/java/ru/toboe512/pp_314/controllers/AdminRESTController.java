package ru.toboe512.pp_314.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.toboe512.pp_314.dto.UserDTO;
import ru.toboe512.pp_314.service.UserDTOService;
import ru.toboe512.pp_314.service.UserService;
import ru.toboe512.pp_314.utils.UserErrorResponse;
import ru.toboe512.pp_314.utils.UserNotCreatedException;
import ru.toboe512.pp_314.utils.UserNotUniqueException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRESTController {

    private final UserService userService;
    private  final UserDTOService userDTOService;

    @Autowired
    public AdminRESTController(UserService userService, UserDTOService userDTOService) {
        this.userService = userService;
        this.userDTOService = userDTOService;
    }

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userDTOService.userDAOListToUserDTOList(userService.listUsers());
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO user,
                                             BindingResult bindingResult) {
        userService.add(userDTOService.userDTOToUserDAO(user, bindingResult));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> edit(@RequestBody @Valid UserDTO user,
                       BindingResult bindingResult) {
        userService.update(userDTOService.userDTOToUserDAO(user, bindingResult));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.delete(userService.getUser(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        return new ResponseEntity<>(new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotUniqueException e) {
        return new ResponseEntity<>(new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        ), HttpStatus.I_AM_A_TEAPOT);
    }
}
