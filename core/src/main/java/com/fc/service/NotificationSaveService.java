package com.fc.service;

import com.fc.domain.Notification;
import com.fc.domain.NotificationRepository;
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
