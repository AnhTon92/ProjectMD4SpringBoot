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
    private Long user_id;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String avatar;

    @Column(unique = true, length = 15)
    private String phone;

    @Column(nullable = false, length = 255)
    private String address;

    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();

    @Temporal(TemporalType.DATE)
    private Date updated_at;

    @Column(nullable = false)
    private Boolean is_deleted = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
