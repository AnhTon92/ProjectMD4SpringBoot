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
public class ProductRequest {
    @NotNull(message = "productName must be not null")
    @NotEmpty(message = "productName must be not empty")
    @NotBlank(message = "productName must be not blank")
    private String productName;
    @Min(value = 1, message = "price must be than 0")
    private Double unitPrice;
    private String description;
    @Min(value = 1, message = "price must be than 0")
    private Integer stockQuantity;
    private Long categoryId;
}
