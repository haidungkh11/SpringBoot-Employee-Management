package com.luv2code.springboot.thymeleafdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class User {
    @Id
    @Column(name="user_id")
    private String userName;

    @Column(name="pw")
    private String passWord;

    @Column(name="active")
    private boolean active;
}
