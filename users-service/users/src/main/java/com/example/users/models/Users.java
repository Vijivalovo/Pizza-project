package com.example.users.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "name2", nullable = false)
    private String name2;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "number_phone", nullable = false)
    private String number_phone;

    @Column(name = "scores", nullable = false)
    private String scores;

    @Column(name = "role", nullable = false)
    private Boolean role;
}
