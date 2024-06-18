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
public class ChangePasswordRequest {
    @NotNull(message = "oldPassword must not be null")
    @NotEmpty(message = "oldPassword must not be empty")
    @NotBlank(message = "oldPassword must not be blank")
    private String oldPassword;
    @NotNull(message = "newPassword must not be null")
    @NotEmpty(message = "newPassword must not be empty")
    @NotBlank(message = "newPassword must not be blank")
    private String newPassword;
    @NotNull(message = "confirmPassword must not be null")
    @NotEmpty(message = "confirmPassword must not be empty")
    @NotBlank(message = "confirmPassword must not be blank")
    private String confirmPassword;
}
