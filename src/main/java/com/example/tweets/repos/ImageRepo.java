package com.example.tweets.repos;

import com.example.tweets.domain.Image;
import com.example.tweets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findByOwner (User user);
}
