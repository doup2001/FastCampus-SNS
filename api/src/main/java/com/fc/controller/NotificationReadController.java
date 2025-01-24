package com.fc.controller;

import com.fc.response.SetLastReadAtResponse;
import com.fc.service.LastReadAtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-notifications")
public class NotificationReadController implements NotificationReadControllerSpec {

    private final LastReadAtService service;

    // 알림목록을 읽었을 때, 현재 시간으로 업데이트 해주는 컨트롤러
    @Override
    @PutMapping("/{userId}/read")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable long userId
    ) {

        Instant latestReadAt = service.setLatestReadAt(userId);
        return new SetLastReadAtResponse(latestReadAt);
    }
}
