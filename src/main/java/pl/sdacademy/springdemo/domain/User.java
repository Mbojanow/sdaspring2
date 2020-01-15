package pl.sdacademy.springdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Role> roles = new ArrayList<>();
}
