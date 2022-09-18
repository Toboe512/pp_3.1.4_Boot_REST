package ru.toboe512.pp_314.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Size(min = 2, max = 25, message = "Lastname must be between 2 and 25 characters")
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastName;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Min(value = 0, message = "Age must be a positive number")
    private int age;

    @Size(min = 4, max = 61, message = "Password must be between 4 and 60 characters")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Roles cannot be empty")
    private String[] roles;

    public  UserDTO() {

    }
    public UserDTO(Long id, String name, String lastName,
                   String email, int age, String password,
                   String[] roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
