package com.zancheema.pos.service.billing.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
