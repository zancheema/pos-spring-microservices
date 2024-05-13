package com.zancheema.pos.service.billing.invoiceitem;

import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemCreationPayload;
import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemInfo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/invoice-items")
public class InvoiceItemController {
    private final InvoiceItemService invoiceItemService;

    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public List<InvoiceItemInfo> getInvoiceItems() {
        return invoiceItemService.getInvoiceItems();
    }

    @PostMapping("/add")
    public ResponseEntity<InvoiceItemInfo> addInvoiceItem(@RequestBody @Valid InvoiceItemCreationPayload payload) {
        return ResponseEntity.of(invoiceItemService.addInvoiceItem(payload));
    }
}
