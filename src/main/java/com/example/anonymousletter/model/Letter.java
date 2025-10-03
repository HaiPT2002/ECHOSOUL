package com.example.anonymousletter.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "letters")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user; // Có thể null (thư ẩn danh)

    // getters and setters
}
