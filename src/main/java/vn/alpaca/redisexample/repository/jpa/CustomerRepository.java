package vn.alpaca.redisexample.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.alpaca.redisexample.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
