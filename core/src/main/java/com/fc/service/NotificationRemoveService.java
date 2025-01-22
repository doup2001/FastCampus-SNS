package com.fc.service;

import com.fc.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRemoveService {

    private final NotificationRepository repository;

    public void deletebyId(String id) {
        repository.deleteById(id);
    }

}
