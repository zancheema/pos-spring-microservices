package com.zancheema.pos.service.billing.invoice;

import com.zancheema.pos.service.billing.customer.Customer;
import com.zancheema.pos.service.billing.customer.CustomerRepository;
import com.zancheema.pos.service.billing.customer.dto.CustomerInfo;
import com.zancheema.pos.service.billing.invoice.dto.InvoiceCreationPayload;
import com.zancheema.pos.service.billing.invoice.dto.InvoiceInfo;
import com.zancheema.pos.service.billing.invoiceitem.InvoiceItem;
import com.zancheema.pos.service.billing.invoiceitem.InvoiceItemRepository;
import com.zancheema.pos.service.billing.messaging.KafkaTopicConfig;
import com.zancheema.pos.service.billing.messaging.StockSold;
import com.zancheema.pos.service.billing.stock.Stock;
import com.zancheema.pos.service.billing.stock.StockRepository;
import com.zancheema.pos.service.billing.util.StreamUtil;
import com.zancheema.pos.service.billing.util.UserContextHolder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final StockRepository stockRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, StockRepository stockRepository, InvoiceItemRepository invoiceItemRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.stockRepository = stockRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<InvoiceInfo> getInvoices() {
        return StreamUtil.stream(invoiceRepository.findAll())
                .map(invoice -> new InvoiceInfo(
                        invoice.getInvoiceNo().toString(),
                        invoice.getCreatedAt(),
                        new CustomerInfo(
                                invoice.getCustomer().getCustomerCode().toString(),
                                invoice.getCustomer().getPhoneNumber()
                        )
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InvoiceInfo> addInvoice(InvoiceCreationPayload payload) {
        Customer customer;
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(payload.getCustomerPhoneNumber());
        customer = optionalCustomer.orElseGet(() ->
                customerRepository.save(new Customer(null, payload.getCustomerPhoneNumber())));


        Invoice invoice = new Invoice(null, LocalDateTime.now(), customer);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        InvoiceInfo invoiceInfo = new InvoiceInfo(
                savedInvoice.getInvoiceNo().toString(),
                savedInvoice.getCreatedAt(),
                new CustomerInfo(
                        savedInvoice.getCustomer().getCustomerCode().toString(),
                        savedInvoice.getCustomer().getPhoneNumber())
        );

        for (InvoiceCreationPayload.InvoiceItem item : payload.getInvoiceItems()) {
            Optional<Stock> optionalStock = stockRepository.findById(item.getStockId());
            if (optionalStock.isEmpty()) {
                return Optional.empty();
            }
            // update quantity
            Stock stock = optionalStock.get();
            int newQuantity = stock.getQuantity() - item.getQuantity();
            stock.setQuantity(newQuantity);
            stockRepository.save(stock);

            InvoiceItem invoiceItem = new InvoiceItem(0L, savedInvoice, stock, item.getQuantity());
            invoiceItemRepository.save(invoiceItem);

            // publish stock-sold event
            kafkaTemplate.send(KafkaTopicConfig.TOPIC_STOCK_SOLD, new StockSold(
                    stock.getId(), item.getQuantity(), UserContextHolder.getContext().getCorrelationId()
            ));
        }

        return Optional.of(invoiceInfo);
    }
}
