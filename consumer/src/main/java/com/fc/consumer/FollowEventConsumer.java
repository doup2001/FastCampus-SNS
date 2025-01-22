package com.fc.consumer;

import com.fc.event.FollowEvent;
import com.fc.event.FollowEventType;
import com.fc.task.FollowAddTask;
import com.fc.task.FollowRemoveTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;
    private final FollowRemoveTask followRemoveTask;


    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if (event.getType() == FollowEventType.ADD) {
                followAddTask.processEvent(event);
            } else if (event.getType() == FollowEventType.REMOVE) {
                followRemoveTask.processEvent(event);
            }
        };
    }



}
