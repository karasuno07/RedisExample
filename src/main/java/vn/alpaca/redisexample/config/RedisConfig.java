package vn.alpaca.redisexample.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

@Configuration
public class RedisConfig {

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() throws IOException {
        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://127.0.0.1:6001")
//                .addNodeAddress("redis://127.0.0.1:6002")
//                .addNodeAddress("redis://127.0.0.1:6003")
//                .addNodeAddress("redis://127.0.0.1:6004")
//                .addNodeAddress("redis://127.0.0.1:6005")
//                .addNodeAddress("redis://127.0.0.1:6006")
//                .setScanInterval(2000);
//        As single instance mode
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setClientName("standalone");

        // TODO: we can store config as file using config.toYAML() / toJSON()

        return Redisson.create(config);
    }

    @Bean
    public RedissonConnectionFactory factory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory factory
    ) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

}
