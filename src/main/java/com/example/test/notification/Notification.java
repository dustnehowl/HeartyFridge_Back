package com.example.test.notification;

import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Notification {
    public enum Category {
        MESSAGE,
        RESERVE
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTIFICATION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private String message;
    private Category type;
    //private String type;
    private LocalDate noticeTime;
    @Setter
    private Boolean isCheck;

    @Builder
    public Notification(Member member, String message,Category category, LocalDateTime noticeTime, Boolean isCheck){
        this.member = member;
        this.message = message;
        this.type = category;
        this.noticeTime = noticeTime.toLocalDate();
        this.isCheck = isCheck;
    }

    public Notification() {

    }
}
