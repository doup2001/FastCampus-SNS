package com.fc.task;

import com.fc.client.PostClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.domain.Post;
import com.fc.event.LikeEvent;
import com.fc.service.NotificationGetService;
import com.fc.service.NotificationRemoveService;
import com.fc.service.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

    private final PostClient postClient;
    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;
    private final NotificationSaveService saveService;

    public void processEvent(LikeEvent event){

        Instant now = Instant.now();

        // 글 작성자와 동일하다면 알림 x
        Post post = postClient.getPost(event.getPostId());

        if (Objects.equals(post.getUserId(), event.getUserId())){
            log.error("글 작성자와 좋아요 누른 {}이 같습니다.", event.getUserId());
            return;
        }

        // 값 가져오기
        Optional<Notification> optionalNotification = getService.findByTypeAndPostId(NotificationType.LIKE, post.getId());
        if (optionalNotification.isEmpty()){
            log.error("LikeNotification 값이 존재하지않습니다");
            return;
        }

        // 삭제하기
        LikeNotification notification = (LikeNotification) optionalNotification.get();
        notification.removeLiker(event.getUserId(), now);

        // 알림이 하나도 없다면 삭제, 알림이 하나라도 있으면 업데이트
        if (notification.getLikerIds().isEmpty()){
            removeService.deletebyId(notification.getId());
        } else {
            saveService.upsert(notification);
        }



    }
}
