package com.zancheema.pos.service.billing.invoiceitem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemCreationPayload {
    @NotBlank
    @JsonProperty("invoice")
    private String invoiceNo;

    @NotNull
    @JsonProperty("stock")
    private Long stockId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
