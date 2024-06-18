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
public class AddressRequest {
    @NotNull(message = "address must not be null")
    @NotEmpty(message = "address must not be empty")
    @NotBlank(message = "address must not be blank")
    private String fullAddress;
    @NotNull(message = "phone must not be null")
    @NotEmpty(message = "phone must not be empty")
    @NotBlank(message = "phone must not be blank")
    private String phone;
    @NotNull(message = "receiveName must not be null")
    @NotEmpty(message = "receiveName must not be empty")
    @NotBlank(message = "receiveName must not be blank")
    private String receiveName;
}
