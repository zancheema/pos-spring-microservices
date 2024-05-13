package com.zancheema.pos.service.billing.invoiceitem;

import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemCreationPayload;
import com.zancheema.pos.service.billing.invoiceitem.dto.InvoiceItemInfo;

import java.util.List;
import java.util.Optional;

public interface InvoiceItemService {
    List<InvoiceItemInfo> getInvoiceItems();

    Optional<InvoiceItemInfo> addInvoiceItem(InvoiceItemCreationPayload payload);
}
