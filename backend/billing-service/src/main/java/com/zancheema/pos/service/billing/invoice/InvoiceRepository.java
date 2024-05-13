package com.zancheema.pos.service.billing.invoice;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InvoiceRepository extends CrudRepository<Invoice, UUID> {
}
