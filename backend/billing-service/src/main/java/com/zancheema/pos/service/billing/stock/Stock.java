package com.zancheema.pos.service.billing.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    private long id;

    @Column(nullable = false)
    private UUID itemCode;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private double itemPrice;

    @Column(nullable = false)
    private int batchNo;

    @Column(nullable = false)
    private int quantity;
}
