package com.fc.controller;

import com.fc.response.SetLastReadAtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.Instant;


@Tag(name = "사용자 알림센터 API")
public interface NotificationReadControllerSpec {

    @Operation(summary = "사용자 알림 목록 읽은 시간 기록")
    SetLastReadAtResponse setLastReadAt(
            @Parameter(example = "1") long userId
    );
}

