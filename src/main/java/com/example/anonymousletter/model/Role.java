package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    private String roleName;

    // --- Quan hệ ngược với User ---
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
}
