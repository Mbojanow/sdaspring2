package pl.sdacademy.springdemo.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AppUserDetailsService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  private EntityManager entityManager;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userRepository.findById(username)
        .map(UserDetailsAdapter::new)
        .orElseThrow();
  }
}
