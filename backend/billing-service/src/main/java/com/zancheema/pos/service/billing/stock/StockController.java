package com.zancheema.pos.service.billing.stock;

import com.zancheema.pos.service.billing.stock.dto.StockInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public Collection<StockInfo> getStocks() {
        return stockService.getStocks();
    }
}
