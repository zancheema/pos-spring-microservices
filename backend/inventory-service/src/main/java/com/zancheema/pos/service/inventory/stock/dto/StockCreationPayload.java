package com.zancheema.pos.service.inventory.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCreationPayload {
    @NotBlank
    private String item;

    @NotNull
    @Min(1)
    @JsonProperty("batch_no")
    private Integer batchNo;

    @NotNull
    @Min(1)
    private Integer quantity;
}
