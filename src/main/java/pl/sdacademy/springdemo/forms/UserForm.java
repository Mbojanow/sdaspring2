package pl.sdacademy.springdemo.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotNull
    @Length(min = 3)
    private String username;

    private String password;
    private String confirmPassword;

    @AssertTrue
    public boolean isPasswordMatch() {
        return password.endsWith(confirmPassword);
    }
}
