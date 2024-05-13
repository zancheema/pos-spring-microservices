package com.zancheema.pos.service.inventory.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfo {
    private long id;

    private String name;

    @JsonProperty("is_active")
    private boolean isActive;
}
