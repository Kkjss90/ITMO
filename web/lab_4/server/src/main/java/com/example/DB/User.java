package com.example.DB;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
@NamedQuery(
        name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username=:username")
@NamedQuery(
        name = "User.findByRefreshToken",
        query = "SELECT u FROM User u JOIN FETCH u.results WHERE u.refreshToken=:refreshToken")
@NamedQuery(
        name = "User.findByID",
        query = "SELECT u FROM User u WHERE u.id=:id")
@NamedQuery(
        name = "User.findByIDWithResults",
        query = "SELECT u FROM User u JOIN FETCH u.results WHERE u.id=:id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "refresh_token")
    private String refreshToken;
    @OneToMany(mappedBy = "user")
    private Set<Result> results;
}
