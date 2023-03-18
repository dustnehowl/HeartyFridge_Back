package com.example.test.notification.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.notification.Notification;
import com.example.test.notification.controller.dto.NotificationDto;
import com.example.test.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    public String test() {
        return "OK!";
    }

    public List<NotificationDto> getNotification(Long memberId) {
        Member member = memberRepository.findMemberById(memberId).get();
        List<Notification> notifications = notificationRepository.findNotificationsByMember(member);

        return NotificationDto.of(notifications);
    }

    public NotificationDto notice(Notification notification){
        notificationRepository.save(notification);
        return NotificationDto.from(notification);
    }

    public String checkNotice(Long notificationId){
        Notification notification = notificationRepository.findNotificationsById(notificationId);
        notification.setIsCheck(true);
        notificationRepository.save(notification);
        return "확인 완료!";
    }
}
