package com.ra.projectspringboot.dto.request;

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
public class CategoryRequest {
    @NotNull(message = "categoryName must be not null")
    @NotEmpty(message = "categoryName must be not empty")
    @NotBlank(message = "categoryName must be not blank")
    private String categoryName;
    private String description;
}
