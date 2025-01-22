package com.fc.task;

import com.fc.domain.FollowNotification;
import com.fc.domain.NotificationType;
import com.fc.event.FollowEvent;
import com.fc.service.NotificationSaveService;
import com.fc.utils.NotificationIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public void processEvent(FollowEvent event) {

        Instant now = Instant.now();

        // 이벤트를 바탕으로 팔로우 알림을 생성한다.
        FollowNotification notification = new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getTargetUserId()
        );

        // DB 저장
        saveService.insert(notification);


    }

}
