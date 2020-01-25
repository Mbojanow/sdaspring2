package pl.sdacademy.springdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/**").authenticated()
        .antMatchers("/login").permitAll()
        .and()
        .formLogin()
        .and()
        .csrf().disable()
        .headers().frameOptions().disable();
  }
}
