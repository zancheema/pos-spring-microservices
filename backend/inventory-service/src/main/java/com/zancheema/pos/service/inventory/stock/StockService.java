package com.zancheema.pos.service.inventory.stock;

import com.zancheema.pos.service.inventory.stock.dto.StockCreationPayload;
import com.zancheema.pos.service.inventory.stock.dto.StockInfo;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<StockInfo> getStocks();

    Optional<StockInfo> addStock(StockCreationPayload payload);

    void deleteStock(long id);
}
