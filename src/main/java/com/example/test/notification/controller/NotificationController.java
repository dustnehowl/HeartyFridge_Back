package com.example.test.notification.controller;

import com.example.test.config.generic.Result;
import com.example.test.notification.service.NotificationService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/test")
    public ResponseEntity<Result> test(){
        return ResponseEntity.ok().body(new Result(notificationService.test()));
    }

    @GetMapping("/getNotification")
    public ResponseEntity<Result> getNotification(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(new Result(notificationService.getNotification(
                Long.parseLong(memberId)
        )));
    }

    @PutMapping("/checkNotice")
    public ResponseEntity<Result> checkNotice(ServletRequest servletRequest, @RequestParam Long notificationId){
        return ResponseEntity.ok().body(new Result(notificationService.checkNotice(notificationId)));
    }
}
