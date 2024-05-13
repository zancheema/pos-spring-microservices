package com.zancheema.pos.service.billing.stock;

import com.zancheema.pos.service.billing.stock.dto.StockInfo;

import java.util.Collection;

public interface StockService {
    Collection<StockInfo> getStocks();
}
