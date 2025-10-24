package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Message;
import com.example.anonymousletter.model.Room;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.MessageRepository;
import com.example.anonymousletter.repository.RoomRepository;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public Message sendMessage(Long roomId, Long senderId, String content) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        User sender = userRepository.findById(senderId).orElseThrow();

        Message msg = Message.builder()
                .room(room)
                .sender(sender)
                .content(content)
                .build();
        return messageRepository.save(msg);
    }

    public List<Message> getMessages(Long roomId) {
        return messageRepository.findByRoom_RoomId(roomId);
    }
}
