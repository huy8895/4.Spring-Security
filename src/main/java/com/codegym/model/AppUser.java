package com.codegym.model;

import javafx.beans.DefaultProperty;

import javax.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    @ManyToOne
//    @JoinColumn(name = "role_id")
    private AppRole myRole = new AppRole(2, "ROLE_USER");

    public AppUser() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppRole getAppRole() {
        return myRole;
    }

    public void setAppRole(AppRole myRole) {
        this.myRole = myRole;
    }
}