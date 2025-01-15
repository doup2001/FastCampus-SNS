package com.fc;

import java.util.Optional;

public interface NotificationRepository {

    Optional<Notification> findById(String id);
    Notification save(Notification notification);
    Notification deleteById(String id);

}
