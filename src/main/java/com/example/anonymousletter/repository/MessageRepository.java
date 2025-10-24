package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoom_RoomId(Long roomId);
}
