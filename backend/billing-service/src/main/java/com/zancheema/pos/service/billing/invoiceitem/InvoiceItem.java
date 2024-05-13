package com.zancheema.pos.service.billing.invoiceitem;

import com.zancheema.pos.service.billing.invoice.Invoice;
import com.zancheema.pos.service.billing.stock.Stock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private Invoice invoice;

    @ManyToOne(optional = false)
    private Stock stock;

    @Column(nullable = false)
    private int quantity;
}
