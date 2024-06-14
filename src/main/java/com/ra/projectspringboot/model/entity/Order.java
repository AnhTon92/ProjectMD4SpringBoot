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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(unique = true, nullable = false, length = 100)
    private String serial_number = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Khóa ngoại, cần thiết lập quan hệ với thực thể User

    private Double total_price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(length = 100)
    private String note;

    @Column(length = 100)
    private String receive_name;

    @Column(length = 255)
    private String receive_address;

    @Column(length = 15)
    private String receive_phone;

    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();

    @Temporal(TemporalType.DATE)
    private Date received_at;
}
