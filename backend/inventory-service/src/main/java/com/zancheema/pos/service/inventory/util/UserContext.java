package com.zancheema.pos.service.inventory.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {
    public static final String CORRELATION_ID = "X-Correlation-Id";

    private String correlationId;
}
