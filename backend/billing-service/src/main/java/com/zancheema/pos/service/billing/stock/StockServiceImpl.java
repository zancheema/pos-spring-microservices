package com.zancheema.pos.service.billing.stock;

import com.zancheema.pos.service.billing.stock.dto.StockInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Collection<StockInfo> getStocks() {
        return stockRepository.findAll()
                .stream()
                .map(stock -> new StockInfo(
                        stock.getId(),
                        stock.getItemCode(),
                        stock.getItemName(),
                        stock.getItemPrice(),
                        stock.getBatchNo(),
                        stock.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
