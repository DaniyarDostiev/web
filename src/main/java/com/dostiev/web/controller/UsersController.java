package com.dostiev.web.controller;

import com.dostiev.web.model.User;
import com.dostiev.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "pages/users";
    }

    @GetMapping("/new")
    public String postUsersForm(@ModelAttribute("user") User user) {
        return "pages/add_user";
    }

    @PostMapping("/new")
    public String postUsers(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUsersForm(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute(userService.getById(id));
        return "pages/add_user";
    }

    @PatchMapping("/edit")
    public String editUsers(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete")
    public String deleteUsers(@RequestParam(value = "id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/users";
    }
}
