package pl.sdacademy.springdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.sdacademy.springdemo.services.DatabaseUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final DatabaseUserDetailsService databaseUserDetailsService;

  public SecurityConfig(final DatabaseUserDetailsService databaseUserDetailsService) {
    this.databaseUserDetailsService = databaseUserDetailsService;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/**").authenticated()
        .antMatchers("/h2**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .csrf().disable()
        .headers().frameOptions().disable();
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return databaseUserDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
