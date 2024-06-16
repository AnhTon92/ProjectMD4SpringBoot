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
@Table(name = "OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Khóa ngoại, cần thiết lập quan hệ với thực thể Order

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Khóa ngoại, cần thiết lập quan hệ với thực thể Product

    @Column(nullable = false, length = 100)
    private String name;

    private Double unitPrice;

    @Column(nullable = false)
    private Integer orderQuantity;
}
