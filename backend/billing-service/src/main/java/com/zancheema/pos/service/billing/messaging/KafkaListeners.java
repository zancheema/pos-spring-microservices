package com.zancheema.pos.service.billing.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zancheema.pos.service.billing.stock.Stock;
import com.zancheema.pos.service.billing.stock.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaListeners {
    public static final String GROUP_ID = "licensingservice";

    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;

    public KafkaListeners(StockRepository stockRepository, ObjectMapper objectMapper) {
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_STOCK_ADDED, groupId = GROUP_ID)
    public void listenToStockAdded(ConsumerRecord<String, Object> record) throws JsonProcessingException {
        StockAdded msgBody = objectMapper.readValue(record.value().toString(), StockAdded.class);
        log.info("StockAdded event with correlation id " + msgBody.getCorrelationId());
        Stock stock = new Stock(
                msgBody.getId(),
                msgBody.getItemCode(),
                msgBody.getItemName(),
                msgBody.getItemPrice(),
                msgBody.getBatchNo(),
                msgBody.getQuantity()
        );
        stockRepository.save(stock);
    }
}
