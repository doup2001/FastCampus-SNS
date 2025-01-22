package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository repository;

    public void insert(Notification notification){
        log.info("Inserted {}", notification);
        repository.insert(notification);
    }

    public void upsert(Notification notification){
        log.info("Upserted {}", notification);
        repository.save(notification);
    }

}
