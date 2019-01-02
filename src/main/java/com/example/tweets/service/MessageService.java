package com.example.tweets.service;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import com.example.tweets.domain.dto.MessageDTO;
import com.example.tweets.repos.ImageRepo;
import com.example.tweets.repos.MessageRepo;
import com.example.tweets.repos.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final ImageRepo imageRepo;
    private final UserRepo userRepo;

    public MessageService(MessageRepo messageRepo, ImageRepo imageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.imageRepo = imageRepo;
        this.userRepo = userRepo;
    }

    public Message save (Message message){
        return messageRepo.save(message);
    }

    public Page<MessageDTO> findAll (Pageable pageable, User user){
        return  messageRepo.findAll(pageable, user);
    }

    public Page<MessageDTO> findByTag (String tag, Pageable pageable, User user){
        return messageRepo.findByTag(tag, pageable, user);
    }

    public Image getImageById (Long id){
        return imageRepo.getOne(id);
    }

    public void deleteMessage(Message message) {
        messageRepo.delete(message);
    }

    public void deleteMessageImage (Message message){
        if (message.getImage()!=null){
            imageRepo.delete(message.getImage());
            message.setImage(null);
        }
    }

    public void like(User user, Message message) {
        if (message.getLikes().contains(user)){
            message.getLikes().remove(user);
        } else {
            message.getLikes().add(user);
        }
        messageRepo.save(message);
    }

    @Transactional
    public Page<MessageDTO> findByTagFollowMessages(User user, String filter, Pageable pageable) {
        Set<User> subscriptions = userRepo.findSubscriptions(user.getId());
        subscriptions.add(user);
        return messageRepo.findByAuthorInAndTag(subscriptions, filter, pageable, user);
    }

    @Transactional
    public Page<MessageDTO> findFollowMessages(User user, Pageable pageable) {
        Set<User> subscriptions = userRepo.findSubscriptions(user.getId());
        subscriptions.add(user);
        return messageRepo.findByAuthorIn(subscriptions, pageable, user);
    }

    public Page<MessageDTO> findByAuthor (User user, Pageable pageable){
        return messageRepo.findByAuthorIn(Collections.singleton(user), pageable, user);
    }

    public Page<MessageDTO> findByAuthorAndTag (User user, Pageable pageable, String tag){
        return messageRepo.findByAuthorInAndTag(Collections.singleton(user), tag, pageable, user);
    }
}
