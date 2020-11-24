package com.esgi.fyc.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Objects;

@Entity(name = "users")
@Table(name = "users",
        indexes = {@Index(name = "users_index", columnList = "user_id", unique = true),
                @Index(name = "user_usn_index", columnList = "username", unique = true)},
        schema = "cook4me_db")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotBlank
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Roles roles;

    public Users() {
    }

    public Users(String username, String password, String firstName, String lastName, String mail, String phoneNumber, Roles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return username.equals(users.username) &&
                roles.getRoleName().equals(users.roles.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, roles);
    }
}
