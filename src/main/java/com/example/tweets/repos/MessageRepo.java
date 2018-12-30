package com.example.tweets.repos;

import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByTag (String tag);

    List<Message> findByAuthorIn(Set<User> authors);

    List<Message> findByAuthorInAndTag (Set<User> authors, String tag);
}
