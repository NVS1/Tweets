package com.example.tweets.controller;

import com.example.tweets.domain.User;
import com.example.tweets.service.UserService;
import com.example.tweets.util.ControllerUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

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
    public String editProfile (@Valid User userEdit,
                               BindingResult bindingResult,
                               Model model,
                               @AuthenticationPrincipal User user
                               ){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            model.addAttribute("userEdit", userEdit);
            return "profile";
        }
        if (userService.updateProfile(user, userEdit.getName(), userEdit.getEmail(), userEdit.getPassword())) {
            model.addAttribute("msg", "Check your mail and confirm new password");
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/profile";
    }

    @PostMapping("/avatar")
    public String setAvatar (@AuthenticationPrincipal User user,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam String url) throws IOException {
        System.out.println(file.getOriginalFilename());
        userService.setAvatar(file, user);
        return "redirect:"+url;
    }
    @GetMapping("user/{subscribe}/{id}")
    public String subscribe (@PathVariable String subscribe,
                             @PathVariable("id") User user,
                             @AuthenticationPrincipal User currentUser){
        if (subscribe.equals("subscribe")){
            userService.subscribe(user, currentUser);
        } else if (subscribe.equals("unsubscribe")){
            userService.unsubscribe(user, currentUser);
        }
        return "redirect:/tweets/"+user.getId();
    }
}
