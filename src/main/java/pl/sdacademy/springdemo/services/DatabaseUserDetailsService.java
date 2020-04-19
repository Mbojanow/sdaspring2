package pl.sdacademy.springdemo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public DatabaseUserDetailsService(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    // działamy na klasie User
    // potrzebujemy interfejs UserDetails
    // potrzeujemy użyć wzorzec projektowy... ???????????????????????
    // ADAPTER (Władysław + 10 punktów ;))
    return new UserDetailsAdapter(userService.getUserByUsername(username));
  }
}
