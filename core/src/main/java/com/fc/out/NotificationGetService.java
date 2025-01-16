package com.fc.out;

import com.fc.domain.notificaition.Notification;
import com.fc.domain.notificaition.NotificationRepository;
import com.fc.domain.notificaition.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> get(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }

}
