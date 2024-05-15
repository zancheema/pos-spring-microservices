package com.zancheema.pos.service.inventory.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zancheema.pos.service.inventory.stock.Stock;
import com.zancheema.pos.service.inventory.stock.StockRepository;
import com.zancheema.pos.service.inventory.util.UserContextHolder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;

@Configuration
public class KafkaListeners {
    public static final String GROUP_ID = "licensingservice";

    Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaListeners(StockRepository stockRepository, ObjectMapper objectMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_STOCK_SOLD, groupId = GROUP_ID)
    public void listenToStockSold(ConsumerRecord<String, Object> record) throws JsonProcessingException {
        StockSold msgBody = objectMapper.readValue(record.value().toString(), StockSold.class);

        Optional<Stock> optionalStock = stockRepository.findById(msgBody.getStockId());
        if (optionalStock.isEmpty()) {
            logger.error("optionalStock with id " + msgBody.getStockId() + " does not exist.");
            return;
        }
        Stock stock = optionalStock.get();

        int newQuantity = stock.getQuantity() - msgBody.getQuantity();
        stock.setQuantity(newQuantity);
        stockRepository.save(stock);

        UserContextHolder.getContext().setCorrelationId(msgBody.getCorrelationId());
    }
}
