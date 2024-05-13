package com.zancheema.pos.service.billing.invoice;

import com.zancheema.pos.service.billing.invoice.dto.InvoiceCreationPayload;
import com.zancheema.pos.service.billing.invoice.dto.InvoiceInfo;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<InvoiceInfo> getInvoices();

    Optional<InvoiceInfo> addInvoice(InvoiceCreationPayload payload);
}
