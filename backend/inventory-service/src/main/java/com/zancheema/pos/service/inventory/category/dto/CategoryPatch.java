package com.zancheema.pos.service.inventory.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPatch {
    private long id;
    private String name;
    private Boolean isActive;
}
