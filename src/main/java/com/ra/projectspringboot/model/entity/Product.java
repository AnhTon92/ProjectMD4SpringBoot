package com.ra.projectspringboot.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.VarcharUUIDJdbcType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 100)
    private String sku = UUID.randomUUID().toString();
    @Column(unique = true, nullable = false, length = 100)
    private String productName;
    private Double unitPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer stockQuantity;
    @Column(length = 255)
    private String image;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    private Boolean isDelete;

}
