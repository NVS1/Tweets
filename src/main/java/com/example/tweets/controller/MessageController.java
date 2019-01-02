package com.example.tweets.controller;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import com.example.tweets.domain.dto.MessageDTO;
import com.example.tweets.service.MessageService;
import com.example.tweets.util.ControllerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String follow(Model model,
                       @RequestParam(required = false, defaultValue = "") String filter,
                       @AuthenticationPrincipal User user,
                       @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageDTO> page;
        if (!filter.isEmpty()){
            page = messageService.findByTagFollowMessages(user,filter, pageable);
        } else {
            page = messageService.findFollowMessages(user, pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        return "index";
    }

    @GetMapping("/all")
    public String allTweets (Model model,
                          @RequestParam(required = false, defaultValue = "") String filter,
                          @AuthenticationPrincipal User user,
                          @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<MessageDTO> page;
        if (!filter.isEmpty()){
            page = messageService.findByTag(filter, pageable, user);
        } else {
            page = messageService.findAll(pageable, user);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/all");
        return "allTweets";
    }

    @PostMapping("/")
    public String addMessage(@Valid Message message,
                             BindingResult bindingResult,
                             Model model,
                             @AuthenticationPrincipal User user,
                             @RequestParam(value = "file", required = false)MultipartFile file,
                             @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam String url) {
        message.setAuthor(user);
        message.setDate(new Date());
        if (bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);
            model.addAttribute("url", "/");
            model.addAttribute("page", messageService.findFollowMessages(user,pageable));
            return "index";
        } else {
            uploadImage(message, file);
            model.addAttribute("message", null);
            messageService.save(message);
        }
        model.addAttribute("url", "/");
        model.addAttribute("page", messageService.findFollowMessages(user,pageable));
        return "redirect:"+url;
    }

    private void uploadImage(Message message, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            Image image = new Image();
            try {
                image.setContent(file.getBytes());
                image.setOriginalFileName(file.getOriginalFilename());
                message.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage (@PathVariable("id") Long id){
        Image image = messageService.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/tweet/delete/{id}")
    public String deleteMessage (@PathVariable("id") Message message,
                                 @AuthenticationPrincipal User user,
                                 @RequestParam String url){
        if (message.getAuthor().equals(user)){
            messageService.deleteMessage(message);
        }
        return "redirect:"+url;
    }

    @PostMapping("/tweet/edit/{id}")
    public String editMessage (@RequestParam String text,
                               @RequestParam String tag,
                               @PathVariable("id") Message message,
                               @AuthenticationPrincipal User user,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam(required = false) String delete,
                               @RequestParam String currentUrl){

        if (message.getAuthor().equals(user)){
           if (!StringUtils.isEmpty(text)){
               message.setText(text);
           }
           if (tag!=null){
               message.setTag(tag);
           }
           if (delete!=null){
               messageService.deleteMessageImage(message);
           }
           uploadImage(message,file);
           message.setDate(new Date());
           messageService.save(message);
        }
        return "redirect:"+currentUrl;
    }

    @GetMapping("/tweets/{id}")
    public String userTweets (@PathVariable("id") User user,
                              @AuthenticationPrincipal User currentUser,
                              Model model,
                              @RequestParam(required = false, defaultValue = "") String filter,
                              @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable){

        if (user!=null){
            model.addAttribute("userDTO", user.toDTO(currentUser));
        }
        if (filter.isEmpty()){
            model.addAttribute("page", messageService.findByAuthor(user,pageable));
        } else {
            model.addAttribute("page", messageService.findByAuthorAndTag(user, pageable, filter));
        }
        model.addAttribute("url", "/tweets/{id}");
        return "home";
    }

    @GetMapping("/like/{id}")
    public String like (@RequestParam("url") String url,
                        @AuthenticationPrincipal User user,
                        @PathVariable("id") Message message){

        messageService.like(user, message);
        return "redirect:"+url;
    }

}
