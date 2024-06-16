package com.ra.projectspringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemResponse {
    private Long id;
    private String productName;
    private Double unitPrice;
    private Integer quantity;
}
