package com.ra.projectspringboot.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
     private User user;

    @Column(nullable = false, length = 255)
    private String full_address;

    @Column(length = 15)
    private String phone;

    @Column(length = 50)
    private String receive_name;
}
