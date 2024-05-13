package com.zancheema.pos.service.inventory.stock;

import com.zancheema.pos.service.inventory.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private Item item;

    @Column(nullable = false)
    private int batchNo;

    @Column(nullable = false)
    private int quantity;
}
