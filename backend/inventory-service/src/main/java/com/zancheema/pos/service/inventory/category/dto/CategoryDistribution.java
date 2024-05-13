package com.zancheema.pos.service.inventory.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDistribution {
    @JsonProperty("category_name")
    private String categoryName;

    private int quantity;
}
