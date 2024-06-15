package com.ra.projectspringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JwtResponse {
    private String accessToken;
    private String username;
    private String email;
    private String fullName;
    private Boolean status;
    private String avatar;
    private String phone;
    private String address;
    private Boolean is_deleted;
    private Set<String> roles;
}