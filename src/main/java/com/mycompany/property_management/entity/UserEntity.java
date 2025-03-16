package com.mycompany.property_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ownerName;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String ownerEmail;
    private String phoneNumber;
    private String password;
}
