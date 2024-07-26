package com.devart.helloevents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String userName;

        @Column(unique = true)
        private String email;

        private String password;

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        private RoleEnum role;
    }

