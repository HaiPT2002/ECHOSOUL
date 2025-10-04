package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Stone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
