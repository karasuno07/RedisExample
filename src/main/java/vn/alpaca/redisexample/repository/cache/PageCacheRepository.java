package vn.alpaca.redisexample.repository.cache;

import org.springframework.data.domain.Page;

import java.util.Collection;

public interface PageCacheRepository<T, S> {

    boolean exists();

    boolean elementExists(T key);

    void saveAll(Page<S> page, long ttl);

    void save(S obj, long ttl);

    void update(S obj, long ttl);

    Page<S> findAll();

    S findOne(T key);

    void deleteAll();

    void deleteOne(T key);
}
