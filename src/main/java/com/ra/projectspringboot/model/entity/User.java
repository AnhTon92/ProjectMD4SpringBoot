package com.ra.projectspringboot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String fullName;

    private Boolean status;

    private String password;

    @Column(length = 255)
    private String avatar;

    private String phone;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    private Boolean isDeleted = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
