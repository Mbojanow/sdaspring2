package pl.sdacademy.springdemo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.forms.UserForm;
import pl.sdacademy.springdemo.repositories.RoleRepository;
import pl.sdacademy.springdemo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@Secured("ROLE_ADMIN")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserController(final UserService userService, final RoleRepository roleRepository, final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUsersList(final ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAll());
        modelMap.addAttribute("userForm", new UserForm());
        modelMap.addAttribute("roles", roleRepository.findAll());
        return "users";
    }

    @PostMapping
    public String saveNewUser(@Valid @ModelAttribute final UserForm userForm, final ModelMap modelMap) {
        userService.create(new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword())));
        return getUsersList(modelMap);
    }

    @PostMapping(path = "/remove/{username}")
    public String deleteUser(@PathVariable final String username, final ModelMap modelMap) {
        userService.delete(username);
        return getUsersList(modelMap);
    }

    @GetMapping(path = "/edit/{username}")
    public String getUpdatedUser(@PathVariable final String username, final ModelMap modelMap) {
        modelMap.addAttribute("userForm", new UserForm(username, null, null));
        modelMap.addAttribute("name", username);
        return "users-edit";
    }

    @PostMapping(path = "/edit/{username}")
    public String updateUser(@PathVariable final String username, @Valid @ModelAttribute final UserForm userForm, final ModelMap modelMap) {
        userService.update(username, new User(username, passwordEncoder.encode(userForm.getPassword())));
        return getUsersList(modelMap);
    }

    @PostMapping(path = "/{username}/roles/{role}")
    public String addRoleForUser(@PathVariable final String username,
                                 @PathVariable final String role,
                                 final ModelMap modelMap) {
        userService.addRole(username, role);
        return getUsersList(modelMap);
    }
}
