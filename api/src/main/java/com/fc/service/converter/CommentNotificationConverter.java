package com.fc.service.converter;

import com.fc.client.PostClient;
import com.fc.client.UserClient;
import com.fc.domain.CommentNotification;
import com.fc.domain.Post;
import com.fc.domain.User;
import com.fc.service.dto.CommentConvertedNotificaiton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentNotificationConverter {

    private final UserClient userClient;
    private final PostClient postClient;

    public CommentConvertedNotificaiton convert(CommentNotification notification) {
        Post post = postClient.getPost(notification.getPostId());
        User user = userClient.getUser(notification.getUserId());

        return new CommentConvertedNotificaiton(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }

}
