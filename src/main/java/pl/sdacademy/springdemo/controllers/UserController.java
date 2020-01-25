package pl.sdacademy.springdemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

  @PostMapping(path = "/add")
  public String saveUser(@Valid @ModelAttribute final UserForm userForm, final ModelMap modelMap) {
    userService.createUser(userForm);
    return displayUsers(modelMap);
  }

  @PostMapping("/remove/{username}")
  public String deleteUser(@PathVariable("username") final String username, final ModelMap modelMap) {
    userService.deleteUser(username);
    return displayUsers(modelMap);
//    return "redirect:users";
//    return new RedirectView(USERS_TEMPLATE_PATH);
  }

}
