package com.fc.consumer;

import com.fc.event.CommentEvent;
import com.fc.event.CommentEventType;
import com.fc.task.CommentAddTask;
import com.fc.task.CommentRemoveTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentEventConsumer {

    private final CommentAddTask commentAddTask;
    private final CommentRemoveTask commentRemoveTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if (event.getType() == CommentEventType.ADD) {
                commentAddTask.processEvent(event);
            }
            else if (event.getType() == CommentEventType.REMOVE) {
                commentRemoveTask.processEvent(event);
            }
        };
    }



}
