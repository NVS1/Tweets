package com.example.tweets.repos;

import com.example.tweets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername (String username);

    User findByActivationCode (String code);

    List<User> findByNameStartingWith (String name);

    @Query("SELECT u.subscriptions FROM User u LEFT OUTER JOIN u.subscriptions WHERE u.id=:id")
    Set<User> findSubscriptions (@Param("id") Long id);

}
