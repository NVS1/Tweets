package com.example.tweets.controller;

import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration (){
        return "register";
    }

    @PostMapping("/registration")
    public String addUser (User user, Model model){
        if (!userService.addUser(user)){
            model.addAttribute("message", "This login is already used");
            return "register";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate (@PathVariable("code") String code, Model model){
        if (userService.isActivated(code)) {
            model.addAttribute("message", "User successful activated!");
        } else {
            model.addAttribute("message", "Activation code is invalid");
        }
        return "login";
    }
}
