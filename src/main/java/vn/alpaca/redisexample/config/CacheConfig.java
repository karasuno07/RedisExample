package vn.alpaca.redisexample.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
        implements CachingConfigurer {

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, org.redisson.spring.cache.CacheConfig>
                config = new HashMap<>();
        config.put("customers",
                new org.redisson.spring.cache.CacheConfig(24 * 60 * 1000,
                        12 * 60 * 1000));
        config.put("customer",
                new org.redisson.spring.cache.CacheConfig(10 * 1000, 5 * 1000));
        return new RedissonSpringCacheManager(
                redissonClient,
                config
        );
    }

}
