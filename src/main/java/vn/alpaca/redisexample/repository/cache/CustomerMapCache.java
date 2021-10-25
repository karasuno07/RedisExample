package vn.alpaca.redisexample.repository.cache;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;
import vn.alpaca.redisexample.entity.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomerMapCache implements CacheRepository<Integer, Customer> {

    public final String CACHE_KEY = "customers";

    private final TimeUnit unit = TimeUnit.SECONDS;
    private final RedissonClient client;

    private RMapCache<Integer, Customer> map;

    @PostConstruct
    public void init() {
        map = client.getMapCache(CACHE_KEY);
        map.expire(86400, unit);
    }

    @Override
    public boolean exists() {
        return map.isExists();
    }

    @Override
    public boolean elementExists(Integer key) {
        return map.containsKey(key);
    }

    @Override
    public void saveAll(Collection<Customer> collection, long ttl) {
        Map<Integer, Customer> customers = collection.stream()
                .collect(Collectors.toMap(
                        Customer::getId, Function.identity())
                );
        map.putAll(customers, ttl, unit);
    }

    @Override
    public void save(Customer obj, long ttl) {
        map.fastPutIfAbsent(obj.getId(), obj, ttl, unit);
    }

    @Override
    public void update(Customer obj, long ttl) {
        map.fastPut(obj.getId(), obj, ttl, unit);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Customer findOne(Integer key) {
        return map.get(key);
    }

    @Override
    public void deleteAll() {
        map.delete();
    }

    @Override
    public void deleteOne(Integer key) {
        map.fastRemove(key);
    }


}
