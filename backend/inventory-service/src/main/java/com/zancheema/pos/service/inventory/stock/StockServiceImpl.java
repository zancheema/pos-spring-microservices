package com.zancheema.pos.service.inventory.stock;

import com.zancheema.pos.service.inventory.item.Item;
import com.zancheema.pos.service.inventory.item.ItemRepository;
import com.zancheema.pos.service.inventory.messaging.StockAddedMessagePayload;
import com.zancheema.pos.service.inventory.stock.dto.StockCreationPayload;
import com.zancheema.pos.service.inventory.stock.dto.StockInfo;
import com.zancheema.pos.service.inventory.util.StreamUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.zancheema.pos.service.inventory.messaging.KafkaTopicConfig.TOPIC_STOCK_ADDED;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ItemRepository itemRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public StockServiceImpl(StockRepository stockRepository, ItemRepository itemRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<StockInfo> getStocks() {
        return StreamUtil.stream(stockRepository.findAll())
                .map(stock -> new StockInfo(stock.getId(), stock.getItem().getName(), stock.getBatchNo(), stock.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StockInfo> addStock(StockCreationPayload payload) {
        Optional<Item> optionalItem = itemRepository.findById(UUID.fromString(payload.getItem()));
        if (optionalItem.isEmpty()) {
            return Optional.empty();
        }
        Item item = optionalItem.get();

        Stock stock = new Stock(0L, item, payload.getBatchNo(), payload.getQuantity());
        Stock savedStock = stockRepository.save(stock);
        StockInfo stockInfo = new StockInfo(
                savedStock.getId(),
                savedStock.getItem().getName(),
                savedStock.getBatchNo(),
                savedStock.getQuantity()
        );

        // publish added stock
        kafkaTemplate.send(TOPIC_STOCK_ADDED, new StockAddedMessagePayload(
                savedStock.getId(),
                stock.getItem().getItemCode(),
                stock.getItem().getName(),
                stock.getItem().getRetailPrice(),
                stock.getBatchNo(),
                stock.getQuantity(),
                UUID.randomUUID()
        ));

        return Optional.of(stockInfo);
    }

    @Override
    public void deleteStock(long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
        }
    }
}
