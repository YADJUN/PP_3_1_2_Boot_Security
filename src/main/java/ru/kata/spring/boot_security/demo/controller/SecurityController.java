package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class SecurityController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public SecurityController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userPage(Model model, @AuthenticationPrincipal User user1) {
        User user = userService.findUserById(user1.getId());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/admin";
    }

    @GetMapping("/get")
    public String getUserById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "userInfo";
    }

    @GetMapping("/save")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("allRoles", allRoles);
        return "/saveUser";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("roles") Set<Role> roles) {
        userService.createUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("roles", allRoles);
        return "/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roles") Set<Role> roles, @RequestParam("id") Long id) {
        userService.updateUser(id, user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}