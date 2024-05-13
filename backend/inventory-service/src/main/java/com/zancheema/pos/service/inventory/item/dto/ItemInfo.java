package com.zancheema.pos.service.inventory.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfo {
    @JsonProperty("id")
    private String itemCode;

    private String name;

    @JsonProperty("purchase_price")
    private double purchasePrice;

    @JsonProperty("retail_price")
    private double retailPrice;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("is_active")
    private boolean isActive;
}
