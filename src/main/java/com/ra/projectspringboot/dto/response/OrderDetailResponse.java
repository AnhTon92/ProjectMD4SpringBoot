package com.ra.projectspringboot.dto.response;

import com.ra.projectspringboot.model.entity.Order;
import com.ra.projectspringboot.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDetailResponse {
    private Long id;
    private Product product; // Khóa ngoại, cần thiết lập quan hệ với thực thể Product
    private String name;
    private Double unitPrice;
    private Integer orderQuantity;
}
