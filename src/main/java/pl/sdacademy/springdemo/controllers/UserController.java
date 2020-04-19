package pl.sdacademy.springdemo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.services.UserService;

@Controller
@RequestMapping(path = "/users")
public class UserController {

  private static final String USERS_MODEL_ATTR = "users";
  private static final String USER_FORM_MODEL_ATTR = "userForm";

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String getUsersPage(final ModelMap modelMap) {
    modelMap.addAttribute(USERS_MODEL_ATTR, userService.getAllUsers());
    modelMap.addAttribute(USER_FORM_MODEL_ATTR, new UserForm());
    return "users";
  }

  @PostMapping
  public String handleUserForm(@Valid @ModelAttribute(name = USER_FORM_MODEL_ATTR) final UserForm userForm) {
    userService.createUser(userForm);
    return "redirect:/users";
  }
}
