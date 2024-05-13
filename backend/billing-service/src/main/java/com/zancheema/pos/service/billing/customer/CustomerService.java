package com.zancheema.pos.service.billing.customer;

import com.zancheema.pos.service.billing.customer.dto.CustomerCreationPayload;
import com.zancheema.pos.service.billing.customer.dto.CustomerInfo;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerInfo> getCustomers();

    Optional<CustomerInfo> addCustomer(CustomerCreationPayload payload);

    void deleteCustomer(String customerCode);
}
