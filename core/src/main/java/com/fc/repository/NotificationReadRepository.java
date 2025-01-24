package com.fc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor

// 레디스 관련 DB 설정
public class NotificationReadRepository {

    private final RedisTemplate<String, String> redisTemplate;

    // 레디스에 KeyValue를 통해서 현재 시간으로 기록
    public Instant setReadTime(Long userId) {
        long lastReadAt = Instant.now().toEpochMilli();
        String key = getKey(userId);
        redisTemplate.opsForValue().set(key, String.valueOf(lastReadAt));
        redisTemplate.expire(key, 90, TimeUnit.DAYS); // TTL 설정
        return Instant.ofEpochMilli(lastReadAt);
    }

    // 레디스에서 값 가져오기
    public Instant getReadTime(Long userId) {
        String key = getKey(userId);
        String lastReadAt = redisTemplate.opsForValue().get(key);

        if (lastReadAt == null) {
            return null;
        }

        long lastReadAtLong = Long.parseLong(lastReadAt);
        return Instant.ofEpochMilli(lastReadAtLong);
    }

    private String getKey(Long userId) {
        return userId + ":lastReadAt";
    }

}
