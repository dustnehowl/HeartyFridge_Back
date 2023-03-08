package com.example.test.notification.repository;

import com.example.test.member.Member;
import com.example.test.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationsByMember(Member member);
    Notification findNotificationsById(Long notificationId);
}
