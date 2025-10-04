package com.example.anonymousletter.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "letters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean anonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)  // null nếu anonymous
    private User user;

    public void setUserId(int userId) {
        return ;
    }

    // getters and setters
}
