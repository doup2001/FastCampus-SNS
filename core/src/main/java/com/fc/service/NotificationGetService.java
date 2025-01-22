package com.fc.service;

import com.fc.domain.Notification;
import com.fc.domain.NotificationRepository;
import com.fc.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> findByTypeAndPostId(NotificationType type, Long postId) {
        return repository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> findByTypeAndUserIdAndFollowerId(NotificationType type, Long userId, Long FollowerId) {
        return repository.findByTypeAndUserIdAndFollowerId(type, userId,FollowerId);
    }

}
