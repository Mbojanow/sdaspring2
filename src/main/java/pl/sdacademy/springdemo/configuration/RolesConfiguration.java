package pl.sdacademy.springdemo.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "pl.sdacademy")
@EnableConfigurationProperties(RolesConfiguration.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RolesConfiguration {

  private List<String> roles;
}
