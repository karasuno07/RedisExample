package vn.alpaca.redisexample.repository.cache;

import java.util.Collection;

public interface CacheRepository<T, S> {

    boolean exists();

    boolean elementExists(T key);

    void saveAll(Collection<S> collection, long ttl);

    void save(S obj, long ttl);

    void update(S obj, long ttl);

    Collection<S> findAll();

    S findOne(T key);

    void deleteAll();

    void deleteOne(T key);

}
