package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stoneId;

    private String stoneName;
    private String stoneFuction;
    private String stoneMessage;

    // --- Quan hệ ngược với User ---
    @OneToMany(mappedBy = "stone", cascade = CascadeType.ALL)
    private List<User> users;
}
