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
public class UpdateUserRequest {
    @NotNull(message = "fullName must be not null")
    @NotEmpty(message = "fullName must be not empty")
    @NotBlank(message = "fullName must be not blank")
    private String fullName;
    @NotNull(message = "phone must be not null")
    @NotEmpty(message = "phone must be not empty")
    @NotBlank(message = "phone must be not blank")
    private String phone;
    @NotNull(message = "address must be not null")
    @NotEmpty(message = "address must be not empty")
    @NotBlank(message = "address must be not blank")
    private String address;
}
