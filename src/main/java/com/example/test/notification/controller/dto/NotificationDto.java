package com.example.test.notification.controller.dto;

import com.example.test.notification.Notification;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NotificationDto {
    private Long notificationId;
    private String message;
    private Notification.Category type;
    private LocalDate noticeDate;
    private Boolean isCheck;

    public NotificationDto(Long notificationId, String message, Notification.Category category, LocalDate noticeTime, Boolean isCheck) {
        this.notificationId = notificationId;
        this.message = message;
        this.type = category;
        this.noticeDate = noticeTime;
        this.isCheck = isCheck;
    }

    public static NotificationDto from(Notification notification){
        return new NotificationDto(
            notification.getId(),
            notification.getMessage(),
            notification.getType(),
            notification.getNoticeTime(),
            notification.getIsCheck()
        );
    }
    public static List<NotificationDto> of(List<Notification> notifications) {
        return notifications.stream().map(
                notification -> new NotificationDto(
                        notification.getId(),
                        notification.getMessage(),
                        notification.getType(),
                        notification.getNoticeTime(),
                        notification.getIsCheck()
                )
        ).collect(Collectors.toList());
    }
}
