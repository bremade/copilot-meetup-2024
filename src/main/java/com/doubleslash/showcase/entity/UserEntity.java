package com.doubleslash.showcase.entity;

import com.doubleslash.showcase.validator.CustomEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @CustomEmail
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // getters and setters
}