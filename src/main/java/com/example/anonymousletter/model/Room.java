package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Room {
    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    @Column(name = "roomId")
    private Long roomId;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    private LocalDateTime createdAt = LocalDateTime.now();

}
