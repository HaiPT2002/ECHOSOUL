package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static Object builder() {
    }
}
