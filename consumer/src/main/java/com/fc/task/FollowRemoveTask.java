package com.fc.task;

import com.fc.domain.FollowNotification;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.event.FollowEvent;
import com.fc.service.NotificationGetService;
import com.fc.service.NotificationRemoveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class FollowRemoveTask {

    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;

    public void processEvent(FollowEvent event) {

        // 팔로우
        Optional<Notification> optionalNotification = getService.findByTypeAndUserIdAndFollowerId(NotificationType.FOLLOW, event.getUserId(), event.getTargetUserId());
        if (optionalNotification.isEmpty()){
            log.error("FollowNotification 값이 존재하지않습니다");
        }

        FollowNotification notification = (FollowNotification)optionalNotification.get();
        removeService.deletebyId(notification.getId());

    }

}
