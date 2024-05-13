package com.zancheema.pos.service.inventory.stock;

import com.zancheema.pos.service.inventory.stock.dto.StockCreationPayload;
import com.zancheema.pos.service.inventory.stock.dto.StockInfo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockInfo> getStocks() {
        return stockService.getStocks();
    }

    @PostMapping("/add")
    public ResponseEntity<StockInfo> addStock(@RequestBody @Valid StockCreationPayload payload) {
        return ResponseEntity.of(stockService.addStock(payload));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
