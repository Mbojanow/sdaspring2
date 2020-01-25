package pl.sdacademy.springdemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.services.RoleService;
import pl.sdacademy.springdemo.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private static final String MODEL_USERS_ATTRIBUTE = "users";
  private static final String MODEL_USER_FORM = "userForm";

  private static final String USERS_TEMPLATE_PATH = "users";
  private static final String USERS_EDIT_TEMPLATE_PATH = "users-edit";

  private final UserService userService;
  private final RoleService roleService;

  public UserController(final UserService userService, final RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping
  public String displayUsers(final ModelMap modelMap) {
    final List<User> allUsers = userService.getAllUsers();
    allUsers.forEach(user -> user.setNonAssignedRoles(roleService.getUnassignedRoles(user)));
    modelMap.addAttribute(MODEL_USERS_ATTRIBUTE, allUsers);
    modelMap.addAttribute(MODEL_USER_FORM, new UserForm());
    return USERS_TEMPLATE_PATH;
  }

  @Secured("ROLE_ADMIN")
  @PostMapping(path = "/add")
  public String saveUser(@Valid @ModelAttribute final UserForm userForm, final ModelMap modelMap) {
    userService.createUser(userForm);
    return displayUsers(modelMap);
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/remove/{username}")
  public String deleteUser(@PathVariable("username") final String username, final ModelMap modelMap) {
    userService.deleteUser(username);
    return displayUsers(modelMap);
//    return "redirect:users";
//    return new RedirectView(USERS_TEMPLATE_PATH);
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("{username}/roles/{rolename}")
  public String addRoleToUser(@PathVariable("username") final String username,
                              @PathVariable("rolename") final String rolename,
                              final ModelMap modelMap) {
    userService.addRoleToUser(username, rolename);
    return displayUsers(modelMap);
  }

  @GetMapping("/{username}/edit")
  public String showEditPage(@PathVariable("username") final String username, final ModelMap modelMap) {
    modelMap.addAttribute(MODEL_USER_FORM, UserForm.builder().username(username).build());
    return USERS_EDIT_TEMPLATE_PATH;
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/{username}/edit")
  public String updateUser(@Valid @ModelAttribute final UserForm userForm,
                           @PathVariable("username") final String username,
                           final ModelMap modelMap) {
    userService.updateUser(userForm, username);
    return displayUsers(modelMap);
  }
}
