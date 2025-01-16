package com.fc.out;

import com.fc.domain.notificaition.NotificationRepository;
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
