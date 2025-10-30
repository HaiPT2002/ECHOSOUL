package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    // --- Mối quan hệ với Role ---
    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    // --- Thông tin cơ bản ---
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String zodiac;
    private String hobby;
    private String avatarUrl;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean waiting = false;

    // --- Mối quan hệ với Stone ---
    @ManyToOne
    @JoinColumn(name = "stoneId")
    private Stone stone;

    // --- Mối quan hệ với Room ---
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<Room> roomsAsUser1;

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    private List<Room> roomsAsUser2;

    // --- Mối quan hệ với Letter ---
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Letter> letters;
}
