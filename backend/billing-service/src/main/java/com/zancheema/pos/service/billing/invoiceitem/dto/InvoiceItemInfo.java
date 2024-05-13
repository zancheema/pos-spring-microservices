package com.zancheema.pos.service.billing.invoiceitem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemInfo {
    private long id;

    @JsonProperty("invoice_no")
    private String invoiceNo;

    @JsonProperty("stock")
    private long stockId;

    private int quantity;
}
