package com.fc.response;

import com.fc.service.dto.GetUserNotificationResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.*;

@AllArgsConstructor
@Getter
@Schema(description = "유저 알림 목록 응답")
public class UserNotificationListResponse {

    @Schema(description = "알림 목록")
    private List<UserNotificationResponse> notifications; // 알림 응답

    @Schema(description = "다음 페이지 요청시 전달한 pivot")
    private Instant pivot; // 다음 요청할때 필요

    @Schema(description = "다음 페이지 존재 여부")
    private boolean hasNext; // 응답이 더 있는지 여부

    public static UserNotificationListResponse of(GetUserNotificationResult result) {
        /*
        GetUserNotificationResult는 Converted를 List로 가지고 있기에
        Converted -> UserNotificationResponse로 변환하는 과정을 stream.map을 통해 변환
         */

        List<UserNotificationResponse> notifications = result.getList().stream()
                .map(UserNotificationResponse::of)
                .toList();

        // 다음이 있고, 비어있으면 안된다.
        Instant pivot = (result.getHasNext() && !notifications.isEmpty())
                ? notifications.getLast().getOccurredAt() : null;

        return new UserNotificationListResponse(
                notifications,
                pivot,
                result.getHasNext()
        );
    }

}
