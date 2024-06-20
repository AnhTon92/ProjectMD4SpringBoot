package com.ra.projectspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemRequest {
    @NotNull(message = "productId must be not null")
    private Long productId;
    @Min(value = 1, message = "quantity must be bigger than 0")
    private Integer quantity;
}
