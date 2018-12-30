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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        userService.setAvatar(file, user);
        return "redirect:"+url;
    }

    @GetMapping("/avatar/delete")
    public String deleteAvatar (@RequestParam String url,
                                @AuthenticationPrincipal User user){
        userService.deleteAvatar(user);
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

    @GetMapping("search")
    public String userSearch (@RequestParam String name,
                              Model model,
                              @AuthenticationPrincipal User user){

        List<User> users = userService.findByName(name);
        users.remove(user);
        model.addAttribute("users", users.stream().map(x->x.toDTO(user)).collect(Collectors.toList()));
        return "users";
    }

    @GetMapping("profile/subscriptions/{id}")
    public String subscriptions (@PathVariable("id") User user,
                                 Model model,
                                 @AuthenticationPrincipal User currentUser){
        model.addAttribute("users", user.getSubscriptions()
                .stream()
                .map(x->x.toDTO(currentUser))
                .collect(Collectors.toList()));
        return "users";
    }

    @GetMapping("profile/subscribers/{id}")
    public String subscribers (@PathVariable("id") User user,
                               Model model,
                               @AuthenticationPrincipal User currentUser){

        model.addAttribute("users", user.getSubscribers()
                .stream()
                .map(x->x.toDTO(currentUser))
                .collect(Collectors.toList()));
        model.addAttribute("isSubscribers", true);
        return "users";
    }
}
