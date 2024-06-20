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
    private String sku = UUID.randomUUID().toString();
    private String productName;
    private Double unitPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
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
