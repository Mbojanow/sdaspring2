package pl.sdacademy.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
        .and().csrf().disable()
        .headers().frameOptions().disable();
    }
}
