package com.zancheema.pos.service.inventory.brand.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreationPayload {
    @NotBlank
    private String name;
}
