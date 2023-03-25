package com.example.test.take.controller;

import com.example.test.config.generic.Result;
import com.example.test.take.controller.dto.TakeResponseDto;
import com.example.test.take.service.TakeService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/take")
@RequiredArgsConstructor
public class TakeController {
    private final TakeService takeService;
    public String test(){
        return takeService.test();
    }
    @PostMapping("/takeFood")
    public Result takeFood(ServletRequest servletRequest, @RequestParam Long giveId){
        String memberId = (String) servletRequest.getAttribute("memberId");
        System.out.println("giveId = " + giveId.toString());
        return new Result(takeService.takeFood(memberId, giveId));
    }
    @GetMapping("/getReservation")
    public ResponseEntity<Result> getReservation(ServletRequest servletRequest){
        String takerId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(
                new Result(takeService.getReservation(Long.parseLong(takerId)))
        );
    }
    @GetMapping("/getTakes")
    public ResponseEntity<Result> getTakes(ServletRequest servletRequest){
        String takerId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(
                new Result(takeService.getTakes(Long.parseLong(takerId)))
        );
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Result> cancelTake(ServletRequest servletRequest, @RequestParam Long takeId){
        String takerId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(
                new Result(takeService.cancel(Long.parseLong(takerId), takeId))
        );

    }


    @PutMapping("/checkFood")
    public ResponseEntity<Result> checkFood(ServletRequest servletRequest, @RequestParam Long takeId){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(new Result(takeService.checkTake(Long.parseLong(memberId), takeId)));
    }

    @GetMapping("/numNotDone")
    public Result numNotDone(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return new Result(takeService.numTakesNotDone(memberId));
    }
}
