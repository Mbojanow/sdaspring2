package pl.sdacademy.springdemo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private static final String MODEL_USERS_ATTRIBUTE = "users";
  private static final String MODEL_USER_FORM = "userForm";

  private static final String USERS_TEMPLATE_PATH = "users";

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String displayUsers(final ModelMap modelMap) {
    final List<User> allUsers = userService.getAllUsers();
    modelMap.addAttribute(MODEL_USERS_ATTRIBUTE, allUsers);
    modelMap.addAttribute(MODEL_USER_FORM, new UserForm());
    return USERS_TEMPLATE_PATH;
  }

}
