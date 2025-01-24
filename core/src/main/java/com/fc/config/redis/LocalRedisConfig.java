package com.fc.config.redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Profile("redis")
@Configuration
public class LocalRedisConfig {

    private static final String REDIS_IMAGE_NAME = "redis:7.4.0";
    private static final int REDIS_PORT = 6379;
    private GenericContainer redis;

    @PostConstruct
    public void init() {

        try {
            redis = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE_NAME))
                    .withExposedPorts(REDIS_PORT);
            redis.start();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    @PreDestroy
    public void destroy() {
        try {
            redis.stop();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // RedisFactory 넣기
    // RedisConnectionFactory는 Spring Data Redis에서 Redis 서버와의 연결을 생성하는 역할을 합니다.
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = new RedisStandaloneConfiguration(redis.getHost(), redis.getMappedPort(REDIS_PORT));

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }


}
