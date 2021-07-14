package com.example.health.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String userid;
    private String password;
    private String email;

    public User(String name, String userid, String password, String email) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.email = email;
    }
}
