package com.example.tweets.controller;

import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/profile")
    public String profile (Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile (@AuthenticationPrincipal User user,
                               @RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model){

        if (userService.updateProfile(user, name, email, password)) {
            model.addAttribute("msg", "Check your mail and confirm new password");
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/profile";
    }
}
