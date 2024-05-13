package com.zancheema.pos.service.inventory.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreationPayload {
    @NotBlank
    private String name;

    @NotNull
    @JsonProperty("purchase_price")
    private Double purchasePrice;

    @NotNull
    @JsonProperty("retail_price")
    private Double retailPrice;

    @NotNull
    private Long brand;

    @NotNull
    private Long category;
}
