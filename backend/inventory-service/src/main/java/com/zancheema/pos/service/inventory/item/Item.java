package com.zancheema.pos.service.inventory.item;

import com.zancheema.pos.service.inventory.brand.Brand;
import com.zancheema.pos.service.inventory.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private UUID itemCode;

    @Column(nullable = false)
    private String name;

    private double purchasePrice;

    private double retailPrice;

    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToOne(optional = false)
    private Category category;

    private boolean isActive;
}
