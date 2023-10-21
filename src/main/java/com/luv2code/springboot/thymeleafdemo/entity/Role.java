package com.luv2code.springboot.thymeleafdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @Column(name="user_id")
    private String userName;

    @Column(name="role")
    private String role;

}
