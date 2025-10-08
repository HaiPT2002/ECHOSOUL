package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    private Integer roleId;

    private String username;

    private String password;

    private String zodiac;

    private String hobby;

    private int stoneId;

    @Column(name = "avatarUrl", nullable = false)
    private String avatarUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isWaiting = false;

    // getters and setters
}
