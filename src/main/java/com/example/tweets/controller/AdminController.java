package com.example.tweets.controller;

import com.example.tweets.domain.Role;
import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String users (Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/user/{id}")
    public String userEdit (@PathVariable("id") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/user")
    public String saveEdit (@RequestParam("id") User user,
                            @RequestParam String username,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam(required = false,defaultValue = "false") String active,
                            @RequestParam Map<String, String> form){

        userService.updateUser(name, username, email, form, active, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") User user){
        userService.deleteUser(user);
        return "redirect:/admin/users";
    }
}
