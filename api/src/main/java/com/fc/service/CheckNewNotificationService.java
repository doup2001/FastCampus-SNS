package com.fc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CheckNewNotificationService {

    private final LastReadAtService redisService;

    private final NotificationGetService getService;
    /*
    몽고 DB에 존재하는 알림 중 최신시간 vs 레디스에 존재하는 유저별 최신 읽은 시간
    몽고 DB에 존재하는 알림 중 최신시간이 레디스에 존재하는 유저별 최신 읽은 시간보다 after라는 빨간 색이 뜬다.
    boolean
     */

    public boolean checkNewNotification(Long userId){

        Instant latestUpdatedAt = getService.getLatestUpdatedAt(userId);
        // 조회할 값이 없는것이기에 false
        if (latestUpdatedAt == null){
            return false;
        }

        Instant latestReadAt = redisService.getLatestReadAt(userId);
        // 조회할 값은 있지만, 내가 아직 조회하지않은 것
        if (latestReadAt == null){
            return true;
        }

        return latestUpdatedAt.isAfter(latestReadAt);
    }


}
