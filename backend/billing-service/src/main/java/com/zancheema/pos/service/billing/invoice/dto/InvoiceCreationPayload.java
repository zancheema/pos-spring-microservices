package com.zancheema.pos.service.billing.invoice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreationPayload {
    @NotBlank
    @JsonProperty("customer")
    private String customerPhoneNumber;

    @NotEmpty
    @JsonProperty("invoice_items")
    private List<InvoiceItem> invoiceItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InvoiceItem {
        @NotNull
        @JsonProperty("stock")
        private Long stockId;

        @NotNull
        @Min(1)
        private Integer quantity;
    }
}
