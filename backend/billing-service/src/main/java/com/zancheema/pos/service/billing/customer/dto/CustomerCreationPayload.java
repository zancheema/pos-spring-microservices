package com.zancheema.pos.service.billing.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreationPayload {
    @NotBlank
    @Length(min = 11, max = 15)
    @JsonProperty("phone_number")
    private String phoneNumber;
}
