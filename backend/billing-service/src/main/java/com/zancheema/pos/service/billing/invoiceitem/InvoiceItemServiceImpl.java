package com.zancheema.pos.service.billing.invoiceitem;

import com.zancheema.pos.service.billing.invoice.Invoice;
import com.zancheema.pos.service.billing.invoice.InvoiceRepository;
import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemCreationPayload;
import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemInfo;
import com.zancheema.pos.service.billing.stock.Stock;
import com.zancheema.pos.service.billing.stock.StockRepository;
import com.zancheema.pos.service.billing.util.StreamUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceRepository invoiceRepository;
    private final StockRepository stockRepository;

    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository, InvoiceRepository invoiceRepository, StockRepository stockRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<InvoiceItemInfo> getInvoiceItems() {
        return StreamUtil.stream(invoiceItemRepository.findAll())
                .map(item -> new InvoiceItemInfo(
                        item.getId(),
                        item.getInvoice().getInvoiceNo().toString(),
                        item.getStock().getId(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InvoiceItemInfo> addInvoiceItem(InvoiceItemCreationPayload payload) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(UUID.fromString(payload.getInvoiceNo()));
        Optional<Stock> optionalStock = stockRepository.findById(payload.getStockId());
        if (optionalInvoice.isEmpty() || optionalStock.isEmpty()) {
            return Optional.empty();
        }
        Invoice invoice = optionalInvoice.get();
        Stock stock = optionalStock.get();

        if (payload.getQuantity() > stock.getQuantity()) {
            return Optional.empty(); //
        }

        InvoiceItem invoiceItem = new InvoiceItem(0L, invoice, stock, payload.getQuantity());
        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(invoiceItem);
        InvoiceItemInfo invoiceItemInfo = new InvoiceItemInfo(
                savedInvoiceItem.getId(),
                savedInvoiceItem.getInvoice().getInvoiceNo().toString(),
                savedInvoiceItem.getStock().getId(),
                savedInvoiceItem.getQuantity()
        );

        stock.setQuantity(stock.getQuantity() - savedInvoiceItem.getQuantity());
        if (stock.getQuantity() == 0) {
            stockRepository.delete(stock);
        } else {
            stockRepository.save(stock);
        }

        return Optional.of(invoiceItemInfo);
    }
}
