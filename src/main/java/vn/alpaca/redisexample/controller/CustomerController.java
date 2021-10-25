package vn.alpaca.redisexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.alpaca.redisexample.dto.request.CustomerRequest;
import vn.alpaca.redisexample.dto.response.CustomerResponse;
import vn.alpaca.redisexample.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public Page<CustomerResponse> findAllCustomer() {
        return service.findAll(Pageable.unpaged());
    }

    @GetMapping("/{productId}")
    public CustomerResponse findCustomerById(@PathVariable int productId) {
        return service.findOneById(productId);
    }

    @PostMapping
    public CustomerResponse createCustomer(
            @RequestBody CustomerRequest requestData
    ) {
        return service.createCustomer(requestData);
    }

    @PutMapping("/{productId}")
    public CustomerResponse createCustomer(
            @PathVariable int productId,
            @RequestBody CustomerRequest requestData
    ) {
        return service.updateCustomer(productId, requestData);
    }

    @DeleteMapping("/{productId}")
    public boolean createCustomer(@PathVariable int productId) {
        service.deleteCustomer(productId);
        return true;
    }
}
