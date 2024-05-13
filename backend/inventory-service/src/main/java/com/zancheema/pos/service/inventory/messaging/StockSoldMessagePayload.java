package com.zancheema.pos.service.inventory.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockSoldMessagePayload {
    private long stockId;
    private int quantity;
    private UUID correlationId;
}
