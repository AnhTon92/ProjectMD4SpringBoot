package com.ra.projectspringboot.dto.request;

import jakarta.persistence.Column;
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
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
}
