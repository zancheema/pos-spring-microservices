package com.zancheema.pos.service.billing.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    @JsonProperty("customer_code")
    private String customerCode;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
