package pl.sdacademy.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.springdemo.services.UserService;

@Controller
@RequestMapping(path = "/users")
public class UserController {

  private static final String USERS_MODEL_ATTR = "users";

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String getUsersPage(final ModelMap modelMap) {
    modelMap.addAttribute(USERS_MODEL_ATTR, userService.getAllUsers());
    return "users";
  }
}
