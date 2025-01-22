package com.fc.service;

import com.fc.repository.NotificationRepository;
import com.fc.service.dto.GetUserNotificationsByPivotResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NotificationListService {

    private final NotificationRepository repository;

    // 목록조회 Pivot 방식 vs Paging 처리 하는 방식 존재
    // occurredAt을 기준으로 무한 스크롤이 발생하도록
    public GetUserNotificationsByPivotResult getUserNotificationsByPivot(long userId, Instant occurredAt) {
        if (occurredAt == null) {
            return GetUserNotificationsByPivotResult.of(repository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE)));
        } else {
            return GetUserNotificationsByPivotResult.of(repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE)));
        }
    }

    private static final int PAGE_SIZE = 20;
}
