package com.fc.service.dto;

import com.fc.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.*;

@Getter
@AllArgsConstructor
public class GetUserNotificationsByPivotResult {

    private List<Notification> list;
    private Boolean hasNext;

    public static GetUserNotificationsByPivotResult of(Slice<Notification> slice) {
        return new GetUserNotificationsByPivotResult(
                slice.getContent(),
                slice.hasNext()
        );
    }

}
