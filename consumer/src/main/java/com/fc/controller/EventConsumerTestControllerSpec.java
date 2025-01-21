package com.fc.controller;

import com.fc.event.CommentEvent;
import com.fc.event.LikeEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;


@Tag(name = "Event Consumer 호출 테스트 API")
public interface EventConsumerTestControllerSpec {

    @Operation(

            requestBody = @RequestBody(
                content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = {
                                        @ExampleObject(value = COMMENT_EVENT_PAYLOAD)
                                }
                        )
                }
            )
    )
    void comment(CommentEvent event);

    @Operation(

            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(name = "게시물 좋아요 이벤트", value = LIKE_EVENT_PAYLOAD)
                                    }
                            )
                    }
            )
    )
    void like(LikeEvent event);

    String COMMENT_EVENT_PAYLOAD = """
            {
               "type": "ADD",
               "postId": 1,
               "userId": 2,
               "commentId": 3
             }
            """;

    String LIKE_EVENT_PAYLOAD = """
            {
               "type": "ADD",
               "postId": 1,
               "userId": 2,
               "createAt": "2025-01-22T18:25:43.511Z"
             }
            """;

}
