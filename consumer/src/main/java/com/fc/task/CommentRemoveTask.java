package com.fc.task;

import com.fc.domain.notificaition.NotificationType;
import com.fc.event.CommentEvent;
import com.fc.in.client.PostClient;
import com.fc.in.domain.Post;
import com.fc.out.NotificationGetService;
import com.fc.out.NotificationRemoveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommentRemoveTask {

    private final PostClient postClient;

    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;


    public void processEvent(CommentEvent event) {
        // 작성자는 알림 필요없음
        Post post = postClient.getPost(event.getPostId());

        // 글 작성자와 같다면 알림은 스킵
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }
        getService.get(NotificationType.COMMENT, event.getCommentId())
                .ifPresentOrElse(
                        notification -> removeService.deletebyId(notification.getId()),
                        () -> log.error("Notification not found")
                );
    }

}
