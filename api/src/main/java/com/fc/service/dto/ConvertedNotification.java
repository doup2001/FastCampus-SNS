package com.fc.service.dto;

import com.fc.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ConvertedNotification {

    public String id;
    public NotificationType type;
    public Instant occurredAt;
    public Instant lastUpdatedAt;

}
