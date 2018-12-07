package com.example.tweets.repos;

import com.example.tweets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername (String username);

    User findByActivationCode (String code);

    List<User> findByNameStartingWith (String name);
}
