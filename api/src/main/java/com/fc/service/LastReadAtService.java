package com.fc.service;

import com.fc.repository.NotificationReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class LastReadAtService {

    private final NotificationReadRepository repository;

    // 현재시간으로 읽음처리
    public Instant setLatestReadAt(Long userId) {
        return repository.setReadTime(userId);
    }

    // 레디스에서 나의 읽음시간 가져오기
    public Instant getLatestReadAt(Long userId) {
        return repository.getReadTime(userId);
    }

}
