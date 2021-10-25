package vn.alpaca.redisexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.alpaca.redisexample.dto.mapper.CustomerMapper;
import vn.alpaca.redisexample.dto.request.CustomerRequest;
import vn.alpaca.redisexample.dto.response.CustomerResponse;
import vn.alpaca.redisexample.entity.Customer;
import vn.alpaca.redisexample.repository.cache.CustomerMapCache;
import vn.alpaca.redisexample.repository.jpa.CustomerRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final CustomerMapCache cache;

    public Page<CustomerResponse> findAll(Pageable pageable) {
        log.info("Is cache exists? " + cache.exists());
        Page<Customer> customers;
        customers = repository.findAll(pageable);
        cache.saveAll(customers.getContent(), 3600);

        return customers.map(mapper::convertToResponseModel);
    }

    public CustomerResponse findOneById(int id) {
        log.info("Is cache contain element? " + cache.elementExists(id));
        Customer customer;
        if (cache.elementExists(id)) {
            customer = cache.findOne(id);
        } else {
            customer = repository.findById(id)
                    .orElseThrow(RuntimeException::new);
            cache.save(customer, 3600);
        }

        return mapper.convertToResponseModel(customer);
    }

    public CustomerResponse createCustomer(CustomerRequest requestData) {
        Customer customer = mapper.convertToEntity(requestData);

        Customer savedCustomer = repository.save(customer);
        cache.save(savedCustomer, 3600);

        return mapper.convertToResponseModel(savedCustomer);

    }

    public CustomerResponse
    updateCustomer(int id, CustomerRequest requestData) {
        Customer customer;
        if (cache.elementExists(id)) {
            customer = cache.findOne(id);
        } else {
            customer = repository.findById(id)
                    .orElseThrow(RuntimeException::new);
            cache.save(customer, 3600);
        }

        customer.setFirstName(requestData.getFirstName());
        customer.setLastName(requestData.getLastName());
        customer.setPhoneNumbers(requestData.getPhoneNumbers());
        customer.setEmail(requestData.getEmail());

        Customer updatedCustomer = repository.save(customer);
        cache.update(updatedCustomer, 3600);

        return mapper.convertToResponseModel(updatedCustomer);
    }

    public void deleteCustomer(int id) {
        Customer customer = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        repository.delete(customer);
        cache.deleteOne(id);
    }
}
