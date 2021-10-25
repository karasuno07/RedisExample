package vn.alpaca.redisexample.repository.cache;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import vn.alpaca.redisexample.entity.Customer;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class CustomerPageCache
        implements PageCacheRepository<Integer, Customer> {

    public final String CACHE_KEY = "customer_page";

    private final TimeUnit unit = TimeUnit.SECONDS;
    private final RedissonClient client;

    private RList<Customer> list;

    @PostConstruct
    public void init() {
        list = client.getList(CACHE_KEY);
        list.expire(86400, unit);
    }


    @Override
    public boolean exists() {
        return list.isExists();
    }

    @Override
    public boolean elementExists(Integer key) {
        return false;
    }

    @Override
    public void saveAll(Page<Customer> page, long ttl) {
        
    }

    @Override
    public void save(Customer obj, long ttl) {

    }

    @Override
    public void update(Customer obj, long ttl) {

    }

    @Override
    public Page<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findOne(Integer key) {
        return list.get(0);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteOne(Integer key) {

    }
}
