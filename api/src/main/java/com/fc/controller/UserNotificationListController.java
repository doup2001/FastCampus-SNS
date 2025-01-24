package com.fc.controller;

import com.fc.response.UserNotificationListResponse;
import com.fc.service.GetUserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequiredArgsConstructor

@RequestMapping("/v1/user-notifications")
public class UserNotificationListController implements UserNotificationListControllerSpec{

    private final GetUserNotificationService getUserNotificationService;


    // 유저별 목록 조회
    @Override
    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ) {
        return UserNotificationListResponse.of(
                getUserNotificationService.getUserNotificationByPivot(userId, pivot)
        );
    }
}
