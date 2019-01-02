package com.example.tweets.domain.dto;

import com.example.tweets.domain.Message;

import java.util.Date;

public class MessageDTO {
    private Long id;
    private String text;
    private String tag;
    private Date date;
    private Long authorId;
    private String authorName;
    private Long imageId;
    private Long likes;
    private boolean isLiked;

    public MessageDTO(Message message, Long likes, boolean isLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag= message.getTag();
        this.date = message.getDate();
        this.authorId = message.getAuthor().getId();
        this.authorName = message.getAuthorName();
        this.imageId = message.getImageId();
        this.likes = likes;
        this.isLiked = isLiked;
    }

    public MessageDTO() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
