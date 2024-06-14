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
    private Long product_id;
    @Column(unique = true, nullable = false, length = 100)
    private String sku = UUID.randomUUID().toString();
    @Column(unique = true, nullable = false, length = 100)
    private String product_name;
    private Double unit_price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer stock_quantity;
    @Column(length = 255)
    private String image;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;
    @Temporal(TemporalType.DATE)
    private Date created_at = new Date();
    @Temporal(TemporalType.DATE)
    private Date updated_at;

}
