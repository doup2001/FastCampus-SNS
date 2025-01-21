package com.fc.task;

import com.fc.client.PostClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.domain.Post;
import com.fc.event.LikeEvent;
import com.fc.service.NotificationGetService;
import com.fc.service.NotificationSaveService;
import com.fc.utils.NotificationIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class LikeAddTask {

    private final PostClient postClient;
    private final NotificationGetService getService;
    private final NotificationSaveService saveService;

    public void processEvent(LikeEvent event){

        Post post = postClient.getPost(event.getPostId());

        //글 작성자와 좋아요 누른 사람이 동일하면 알림 필요없음.
        if (Objects.equals(post.getUserId(),event.getUserId())){
            log.error("글 작성자와 좋아요 누른 {}이 같습니다.", event.getUserId());
            return;
        }

        // 기존에 없다면 새로 생섵, 기존에 존재하면 업데이트
        LikeNotification notification = createOrUpdateNotification(post, event);

        // DB 저장
        saveService.upsert(notification);
    }

    private LikeNotification createOrUpdateNotification(Post post, LikeEvent event){
        Instant now = Instant.now();
        Instant retention = now.plus(90, ChronoUnit.DAYS);

        Optional<Notification> optionalNotification = getService.findByTypeAndPostId(NotificationType.LIKE, post.getId());

        // 기존에 없다면 새로 생성
        if (optionalNotification.isEmpty()){
            return createNotification(post, event);

        } else { // 존재 했다면 값 업데이트
            LikeNotification notification = (LikeNotification) optionalNotification.get();
            notification.addLiker(event.getUserId(), event.getCreatedAt(), now, retention);
            return notification;
        }
    }

    private LikeNotification createNotification(Post post, LikeEvent event){
        Instant now = Instant.now();

        return new LikeNotification(
                NotificationIdGenerator.generate(),
                event.getUserId(),
                NotificationType.LIKE,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                List.of(event.getUserId())
        );
    }


}
