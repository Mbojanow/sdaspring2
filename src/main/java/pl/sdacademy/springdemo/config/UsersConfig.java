package pl.sdacademy.springdemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "a")
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersConfig {
    private List<UserData> data;
}
