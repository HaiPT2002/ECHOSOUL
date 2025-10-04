package com.example.anonymousletter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    private String roleName;
}
