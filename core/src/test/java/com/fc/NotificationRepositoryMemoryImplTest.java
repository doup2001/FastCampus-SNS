package com.fc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
@SpringBootTest
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;

    private final Instant now = Instant.now();
    private final Instant deleteAt = Instant.now().plus(90, ChronoUnit.DAYS);

    @Test
    void test_save() {
        sut.save(new Notification("1",1L,NotificationType.LIKE,now,deleteAt));

        Optional<Notification> optionalNotification = sut.findById("1");
        assertTrue(optionalNotification.isPresent());
    }

    @Test
    void findById() {
        sut.save(new Notification("2",1L,NotificationType.LIKE,now,deleteAt));

        Optional<Notification> optionalNotification = sut.findById("2");
        Notification notification = optionalNotification.orElseThrow();

        assertEquals(notification.id, "2");
        assertEquals(notification.userId, 1L);
        assertEquals(notification.createdAt.getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.deleteAt.getEpochSecond(), deleteAt.getEpochSecond());

    }

    @Test
    void deleteById() {
        // given
        sut.save(new Notification("3",1L,NotificationType.LIKE,now,deleteAt));

        // when
        sut.deleteById("3");
        Optional<Notification> optionalNotification = sut.findById("3");

        // then
        assertFalse(optionalNotification.isPresent());
    }
}