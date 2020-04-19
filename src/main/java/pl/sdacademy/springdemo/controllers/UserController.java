package pl.sdacademy.springdemo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.services.UserException;
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
  public String handleUserForm(@Valid @ModelAttribute(name = USER_FORM_MODEL_ATTR) final UserForm userForm,
                               final BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      //throw new UserException()
      return "redirect:/error";
    }
    userService.createUser(userForm);
    return "redirect:/users";
  }

  @PostMapping(path = "/{username}")
  public String handleUserDelete(@PathVariable(name = "username") final String username) {
    userService.deleteUser(username);
    return "redirect:/users";
  }

  //{/users/__${user.getUsername()}__/roles/__${possibleRole}__}
  @PostMapping(path = "/{username}/roles/{rolename}")
  public String handleRoleAdd(@PathVariable final String username, @PathVariable final String rolename) {
    userService.addRoleToUser(username, rolename);
    return "redirect:/users";
  }
}
