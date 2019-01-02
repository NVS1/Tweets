package com.example.tweets.repos;

import com.example.tweets.domain.Message;
import com.example.tweets.domain.User;
import com.example.tweets.domain.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query("select new com.example.tweets.domain.dto.MessageDTO(" +
            "m, " +
            "count (ml), " +
            "(sum (case when ml = :user then 1 else 0 end) > 0)" +
            ") " +
            "from Message m left join m.author left join m.image left join m.likes ml " +
            "where m.tag = :tag " +
            "group by m")
    Page<MessageDTO> findByTag (@Param("tag") String tag, Pageable pageable, @Param("user") User author);

    @Query("select new com.example.tweets.domain.dto.MessageDTO(" +
            "m, " +
            "count (ml), " +
            "(sum (case when ml = :user then 1 else 0 end) > 0)" +
            ") " +
            "from Message m left join m.author left join m.image left join m.likes ml " +
            "where m.author IN :users " +
            "group by m")
    Page<MessageDTO> findByAuthorIn(@Param("users") Set<User> authors, Pageable pageable,  @Param("user") User author);

    @Query("select new com.example.tweets.domain.dto.MessageDTO(" +
            "m, " +
            "count (ml), " +
            "(sum (case when ml = :user then 1 else 0 end) > 0)" +
            ") " +
            "from Message m left join m.author left join m.image left join m.likes ml " +
            "where m.author IN :users " +
            "and m.tag = :tag " +
            "group by m")
    Page<MessageDTO> findByAuthorInAndTag (@Param("users") Set<User> authors, @Param("tag") String tag, Pageable pageable, @Param("user") User author);

    @Query("select new com.example.tweets.domain.dto.MessageDTO(" +
            "m, " +
            "count (ml), " +
            "(sum (case when ml = :user then 1 else 0 end) > 0)" +
            ") " +
            "from Message m left join m.author left join m.image left join m.likes ml " +
            "group by m")
    Page<MessageDTO> findAll (Pageable pageable, @Param("user") User author);
}
