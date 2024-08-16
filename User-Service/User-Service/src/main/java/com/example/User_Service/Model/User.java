package com.example.User_Service.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User {

    @Id
    private Integer id;

    private String name;

    private String email;

    private String password_hashs;

    private String role;  // Assuming role is stored as a String, change to Enum type if needed

    private Timestamp created_at;

    private Timestamp updated_at;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword_hashs() {
        return password_hashs;
    }

    public void setPassword_hashs(String password_hashs) {
        this.password_hashs = password_hashs;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
