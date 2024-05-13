package com.zancheema.pos.service.billing.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockInfo {
    private long id;

    @JsonProperty("item_id")
    private UUID itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_price")
    private double itemPrice;

    @JsonProperty("batch_no")
    private int batchNo;

    private int quantity;
}
