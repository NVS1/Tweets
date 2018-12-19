package com.example.tweets.domain;

import com.example.tweets.domain.dto.MessageDTO;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please, enter your message")
    @Length(max = 255, message = "Message too long")
    private String text;
    @Length(max = 255, message = "Tag too long")
    private String tag;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
    @ManyToMany
    @JoinTable(name = "messages_likes",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    public Message() {

    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAuthorName (){
        return author!=null? author.getName():"anonymous";
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Long getImageId(){
        return image.getId();
    }
    public MessageDTO toDTO (User user){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(id);
        messageDTO.setText(text);
        messageDTO.setTag(tag);
        messageDTO.setDate(date);
        messageDTO.setAuthorId(author.getId());
        messageDTO.setAuthorName(author.getName());
        messageDTO.setImageId(image!=null? image.getId():null);
        messageDTO.setLikes(likes.size());
        messageDTO.setLiked(likes.contains(user));
        return messageDTO;
    }
}