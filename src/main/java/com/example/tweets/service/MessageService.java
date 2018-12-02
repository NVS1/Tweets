package com.example.tweets.service;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.repos.ImageRepo;
import com.example.tweets.repos.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final ImageRepo imageRepo;

    public MessageService(MessageRepo messageRepo, ImageRepo imageRepo) {
        this.messageRepo = messageRepo;
        this.imageRepo = imageRepo;
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
        imageRepo.delete(message.getImage());
        message.setImage(null);
    }
}
