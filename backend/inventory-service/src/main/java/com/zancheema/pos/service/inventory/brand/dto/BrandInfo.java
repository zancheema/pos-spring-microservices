package com.zancheema.pos.service.inventory.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandInfo {
    private long id;
    private String name;
    @JsonProperty("is_active")
    private boolean isActive;
}
