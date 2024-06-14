package com.ra.projectspringboot.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "wishList")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wish_list_id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user; // Khóa ngoại, cần thiết lập quan hệ với thực thể User

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product; // Khóa ngoại, cần thiết lập quan hệ với thực thể Product
}
