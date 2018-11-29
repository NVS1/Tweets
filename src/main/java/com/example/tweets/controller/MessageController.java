package com.example.tweets.controller;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import com.example.tweets.service.MessageService;
import com.example.tweets.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String main(Model model, @RequestParam(required = false, defaultValue = "") String filter) {
        List<Message> messages;
        if (!filter.isEmpty()){
            messages = messageService.findByTag(filter);
        } else {
            messages = messageService.findAll();
        }
        model.addAttribute("messages", messages);
        return "index";
    }

    @PostMapping("/")
    public String addMessage(@Valid Message message,
                             BindingResult bindingResult,
                             Model model,
                             @AuthenticationPrincipal User user,
                             @RequestParam(value = "file", required = false)MultipartFile file) {
        message.setAuthor(user);
        if (bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtil.getErrors(bindingResult);
            model.addAttribute("error", errorMap);
        } else {
            if (file != null && !file.isEmpty()) {
                Image image = new Image();
                try {
                    image.setContent(file.getBytes());
                    message.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("");
            messageService.save(message);
        }

        return "redirect:/";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage (@PathVariable("id") Long id){
        Image image = messageService.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image.getContent(), headers, HttpStatus.OK);
    }
}