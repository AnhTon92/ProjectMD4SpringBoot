package com.ra.projectspringboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressRequest {
    private String fullAddress;
    private String phone;
    private String receiveName;
}
