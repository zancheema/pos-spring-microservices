package com.zancheema.pos.service.inventory.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPatch {
    @NotBlank
    @JsonProperty("item_code")
    private String itemCode;

    private String name;

    @JsonProperty("purchase_price")
    private Double purchasePrice;

    @JsonProperty("retail_price")
    private Double retailPrice;

    private Long brand;

    private Long category;

    @JsonProperty("is_active")
    private Boolean isActive;
}
