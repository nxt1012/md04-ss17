package com.ra.dto;

import com.ra.dto.validate.VietnamesePhoneNumberConstraint;
import com.ra.model.dao.utils.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDTO {

    private int id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    private String password;
    @VietnamesePhoneNumberConstraint
    private int phone;
    private Role role = Role.USER;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String email, String password, int phone, Role role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}