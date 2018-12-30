package com.example.tweets.service;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import com.example.tweets.repos.ImageRepo;
import com.example.tweets.repos.MessageRepo;
import com.example.tweets.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

    public List<Message> findAll (){
        List<Message> all = messageRepo.findAll();
        all.sort(Comparator.comparing(Message::getDate).reversed());
        return all;
    }

    public List<Message> findByTag (String tag){
        List<Message> byTag = messageRepo.findByTag(tag);
        byTag.sort(Comparator.comparing(Message::getDate).reversed());
        return byTag;
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
    public List<Message> findByTagFollowMessages(User user, String filter) {
        Set<User> subscriptions = userRepo.findSubscriptions(user.getId());
        subscriptions.add(user);
        List<Message> followMessages = messageRepo.findByAuthorInAndTag(subscriptions, filter);
        followMessages.sort(Comparator.comparing(Message::getDate).reversed());
        return followMessages;
    }

    @Transactional
    public List<Message> findFollowMessages(User user) {
        Set<User> subscriptions = userRepo.findSubscriptions(user.getId());
        subscriptions.add(user);
        List<Message> followMessages = messageRepo.findByAuthorIn(subscriptions);
        followMessages.sort(Comparator.comparing(Message::getDate).reversed());
        return followMessages;
    }
}
