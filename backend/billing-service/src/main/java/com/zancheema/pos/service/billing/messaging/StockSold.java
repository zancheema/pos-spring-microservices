package com.zancheema.pos.service.billing.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockSold {
    private long stockId;
    private int quantity;
    private String correlationId;
}
