package pl.sdacademy.springdemo.services;

import java.beans.Transient;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.exceptions.SdaException;
import pl.sdacademy.springdemo.repositories.RoleRepository;

@Service
public class RoleService {

  private final RoleRepository roleRepository;

  public RoleService(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Transactional
  public List<Role> getUnassignedRoles(final User user) {
    final List<Role> allRoles = roleRepository.findAll();
    allRoles.removeAll(user.getRoles());
    return allRoles;
  }

  @Transactional
  public Role getByName(final String roleName) {
    return roleRepository.findById(roleName)
        .orElseThrow(() -> new SdaException("Role with name " + roleName + " does not exist"));
  }
}
