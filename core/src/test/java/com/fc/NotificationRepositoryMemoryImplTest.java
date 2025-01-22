package com.fc;

import com.IntegrationTest;
import com.fc.domain.CommentNotification;
import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.domain.NotificationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class NotificationRepositoryMemoryImplTest extends IntegrationTest {


    // SUT는 System Under Test의 약자로, 테스트 대상 시스템을 의미
    @Autowired
    private NotificationRepository sut;

    private final Long userId = 1L;
    private final Long postId = 1L;
    private final Long writerId = 1L;
    private final Long commentId = 1L;
    private final String comment = "comment";
    private final Instant now = Instant.now();
    private final Instant deletedAt = now.plus(90, ChronoUnit.DAYS);


    // Test_이전
    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 5; i++) {
            Instant minusDay = now.minus(i, ChronoUnit.DAYS);
            sut.save(new CommentNotification("id" + i, userId, NotificationType.COMMENT, minusDay, now, now, deletedAt, postId, writerId, commentId, comment));

        }
    }


    // Test_이후
    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }

    @Test
    void test_save() {
        sut.save(new CommentNotification("1", userId, NotificationType.COMMENT, now, now, now, deletedAt, postId, writerId, commentId, comment));

        Optional<Notification> optionalNotification = sut.findById("1");
        assertTrue(optionalNotification.isPresent());
    }

    @Test
    void findById() {
        sut.save(new CommentNotification("1",userId, NotificationType.COMMENT,now,now,now, deletedAt,postId,writerId,commentId,comment));
        sut.save(new CommentNotification("2",userId, NotificationType.COMMENT,now,now,now, deletedAt,postId,writerId,commentId,comment));

        Optional<Notification> optionalNotification = sut.findById("2");
        Notification notification = optionalNotification.orElseThrow();

        assertEquals(notification.id, "2");
        assertEquals(notification.userId, userId);
        assertEquals(notification.createdAt.getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.deleteAt.getEpochSecond(), deletedAt.getEpochSecond());

    }

    @Test
    void deleteById() {
        // given
        sut.save(new CommentNotification("3",3L, NotificationType.COMMENT,now,now,now, deletedAt,3L,3L,3L,"comment3"));

        // when
        sut.deleteById("3");
        Optional<Notification> optionalNotification = sut.findById("3");

        // then
        assertFalse(optionalNotification.isPresent());
    }

    // 최신순서대로 검증하기
    @Test
    void findAllByUserIdOrderByOccurredAtDesc() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);
        Slice<Notification> result = sut.findAllByUserIdOrderByOccurredAtDesc(userId, pageRequest);

        // when
        List<Notification> notifications = result.getContent();
        boolean hasNext =result.hasNext();

        Notification first = notifications.get(0);
        Notification second = notifications.get(1);
        Notification third = notifications.get(2);

        // then
        assertEquals(3, notifications.size());
        assertTrue(hasNext);
        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
        assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt())); // 최신순 정렬

        System.out.println(first.getOccurredAt());
        System.out.println(second.getOccurredAt());
        System.out.println(third.getOccurredAt());
    }

    @Test
    void findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc() {
        // given

        PageRequest pageRequest = PageRequest.of(0, 3);

        Slice<Notification> before = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, now, pageRequest);
        List<Notification> beforeNotifications = before.getContent();
        Notification notification3 = beforeNotifications.get(2);

        Instant pivot = notification3.getOccurredAt();

        // when
        Slice<Notification> result = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, pivot, pageRequest);
        List<Notification> notifications = result.getContent();
        Notification first = notifications.get(0);
        Notification second = notifications.get(1);

        boolean hasNext = result.hasNext();

        // then
        assertFalse(hasNext);
        assertEquals(2, notifications.size());
        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));

        System.out.println(first.getOccurredAt());
        System.out.println(second.getOccurredAt());
    }
}