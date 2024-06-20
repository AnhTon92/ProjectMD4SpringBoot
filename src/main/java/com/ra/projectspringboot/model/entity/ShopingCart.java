package com.ra.projectspringboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "shoppingCart")
public class ShopingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Khóa ngoại, cần thiết lập quan hệ với thực thể Product
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")// Khóa ngoại, cần thiết lập quan hệ với thực thể User
    private Product product;
    private Integer orderQuantity;
}
