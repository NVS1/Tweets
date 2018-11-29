package com.example.tweets.service;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.Message;
import com.example.tweets.repos.ImageRepo;
import com.example.tweets.repos.MessageRepo;
import org.springframework.stereotype.Service;

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
        return messageRepo.findAll();
    }

    public List<Message> findByTag (String tag){
        return messageRepo.findByTag(tag);
    }
    public Image getImageById (Long id){
        return imageRepo.getOne(id);
    }
}
