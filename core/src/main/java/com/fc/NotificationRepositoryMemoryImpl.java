package com.fc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NotificationRepositoryMemoryImpl implements NotificationRepository {

    private final Map<String, Notification> memory = new HashMap<>();

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(memory.get(id));
    }

    @Override
    public Notification save(Notification notification) {
        return memory.put(notification.id, notification);
    }

    @Override
    public Notification deleteById(String id) {
        return memory.remove(id);
    }
}
