package com.fc.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "사용자 신규 알림 여부 조회 응답")
public class CheckNewNotificationResponse {

    @Schema(description = "신규 알림 존재 여부")
    private boolean hasNew;
}
