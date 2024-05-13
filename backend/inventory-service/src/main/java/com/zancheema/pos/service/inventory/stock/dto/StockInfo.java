package com.zancheema.pos.service.inventory.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockInfo {
    private long id;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("batch_no")
    private int batchNo;

    private int quantity;
}
