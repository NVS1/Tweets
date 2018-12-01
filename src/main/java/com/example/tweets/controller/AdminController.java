package com.example.tweets.controller;

import com.example.tweets.domain.Role;
import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import com.example.tweets.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String saveEdit (@Valid User userEdit,
                            BindingResult bindingResult,
                            Model model,
                            @RequestParam("id") User user,
                            @RequestParam(required = false,defaultValue = "false") String active,
                            @RequestParam Map<String, String> form){
        if (bindingResult.hasErrors() || !userService.checkUserByUsername(userEdit, user)){
            if (!userService.checkUserByUsername(userEdit, user)){
                model.addAttribute("usernameError", "This username is already used");
            }
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            model.addAttribute("userEdit", userEdit);
            model.mergeAttributes(errors);
            return "userEdit";
        }
        userService.updateUser(userEdit, form, active, user);

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") User user){
        userService.deleteUser(user);
        return "redirect:/admin/users";
    }
}
