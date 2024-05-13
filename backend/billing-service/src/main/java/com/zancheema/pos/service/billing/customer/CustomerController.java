package com.zancheema.pos.service.billing.customer;

import com.zancheema.pos.service.billing.customer.dto.CustomerCreationPayload;
import com.zancheema.pos.service.billing.customer.dto.CustomerInfo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerInfo> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerInfo> addCustomer(@RequestBody @Valid CustomerCreationPayload payload) {
        return ResponseEntity.of(customerService.addCustomer(payload));
    }

    @DeleteMapping("/delete/{customer_code}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customer_code") String customerCode) {
        customerService.deleteCustomer(customerCode);
        return ResponseEntity.noContent().build();
    }
}
