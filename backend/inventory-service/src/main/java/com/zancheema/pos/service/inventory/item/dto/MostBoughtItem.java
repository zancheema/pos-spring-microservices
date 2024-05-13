package com.zancheema.pos.service.inventory.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MostBoughtItem {
    @JsonProperty("item_name")
    private String itemName;

    private int quantity;
}
