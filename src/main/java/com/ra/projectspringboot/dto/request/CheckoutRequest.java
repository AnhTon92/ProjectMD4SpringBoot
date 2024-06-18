package com.ra.projectspringboot.dto.request;

import jakarta.persistence.Column;
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
public class CheckoutRequest {
    private String note;
    @NotNull(message = "receiveName must not be null")
    @NotEmpty(message = "receiveName must not be empty")
    @NotBlank(message = "receiveName must not be blank")
    private String receiveName;
    @NotNull(message = "receiveAddress must not be null")
    @NotEmpty(message = "receiveAddress must not be empty")
    @NotBlank(message = "receiveAddress must not be blank")
    private String receiveAddress;
    @NotNull(message = "receiPhone must not be null")
    @NotEmpty(message = "receiPhone must not be empty")
    @NotBlank(message = "receiPhone must not be blank")
    private String receivePhone;
}
