package pl.sdacademy.springdemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String username;
    private String password;
    private String role;
}
