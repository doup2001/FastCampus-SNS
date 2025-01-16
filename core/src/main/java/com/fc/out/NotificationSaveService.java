package com.fc.out;

import com.fc.domain.notificaition.Notification;
import com.fc.domain.notificaition.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository repository;

    public void insert(Notification notification){
        repository.insert(notification);
    }

}
