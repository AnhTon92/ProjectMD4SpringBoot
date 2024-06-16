package com.ra.projectspringboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String serialNumber = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Khóa ngoại, cần thiết lập quan hệ với thực thể User

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 100)
    private String note;

    @Column(length = 100)
    private String receiveName;

    private String receiveAddress;

    @Column(length = 15)
    private String receivePhone;

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

    @Temporal(TemporalType.DATE)
    private Date receivedAt;
}
