package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stone")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stone_id")
    private Long stoneId;

    private String stoneName;
    private String stoneFunction;
    private String stoneMessage;

    private String imageUrl; // "/images/stones/stone.png"


    // --- Quan hệ ngược với User ---
    @OneToMany(mappedBy = "stone", cascade = CascadeType.ALL)
    private List<User> users;

    // --- Quan hệ ngược với Letter ---
    @OneToMany(mappedBy = "stone", cascade = CascadeType.ALL)
    private List<Letter> letters;

}
