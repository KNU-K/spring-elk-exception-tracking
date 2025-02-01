package com.example.springelkexceptiontracking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="users")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    public static User create(String name, String email, String password){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
