package com.example.tweets.controller;

import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import com.example.tweets.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration (){
        return "register";
    }

    @PostMapping("/registration")
    public String addUser (@Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "register";
        }
        if (!userService.addUser(user)){
            model.addAttribute("usernameError", "This login is already used");
            return "register";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate (@PathVariable("code") String code, Model model){
        if (userService.isActivated(code)) {
            model.addAttribute("type", "success");
            model.addAttribute("message", "Successful");
        } else {
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Activation code is invalid");
        }
        return "login";
    }
}
