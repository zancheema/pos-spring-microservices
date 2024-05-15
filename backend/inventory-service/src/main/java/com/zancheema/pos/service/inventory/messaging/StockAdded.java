package com.zancheema.pos.service.inventory.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockAdded {
    private long id;
    private UUID itemCode;
    private String itemName;
    private double itemPrice;
    private int batchNo;
    private int quantity;
    private String correlationId;
}
