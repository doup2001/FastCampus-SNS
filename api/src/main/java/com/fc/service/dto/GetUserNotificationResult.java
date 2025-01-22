package com.fc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class GetUserNotificationResult {

    private List<ConvertedNotification> list;
    private Boolean hasNext;

}
