package com.example.anonymousletter.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean anonymous;

    // --- Người viết thư (có thể ẩn danh) ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user; // null nếu ẩn danh

    // --- Viên đá gắn với thư ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stone_id", nullable = true)
    private Stone stone;

    @OneToMany(mappedBy = "letter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses = new ArrayList<>();

    public void setUserId(int userId) {
    }
}
