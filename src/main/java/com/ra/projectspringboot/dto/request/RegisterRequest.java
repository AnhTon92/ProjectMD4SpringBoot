package com.ra.projectspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {
    @NotNull(message = "fullName must not be null")
    @NotEmpty(message = "fullName must not be empty")
    @NotBlank(message = "fullName must not be blank")
    private String fullName;
    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    @NotBlank(message = "password must not be blank")
    private String password;
    private Set<String> roles;
}
