package com.example.test.take.service;

import com.example.test.exception.NotTakerException;
import com.example.test.exception.OnReservedFoodException;
import com.example.test.exception.SameGiverTakerException;
import com.example.test.exception.TooManyReservedException;
import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.notification.Notification;
import com.example.test.notification.service.NotificationService;
import com.example.test.take.Take;
import com.example.test.take.controller.dto.TakeResponseDto;
import com.example.test.take.controller.dto.v2.TakeDtoV2;
import com.example.test.take.repository.TakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TakeService {
    private final TakeRepository takeRepository;
    private final GiveRepository giveRepository;
    private final MemberRepository memberRepository;
    private final NotificationService notificationService;
    public String test() {
        return "OK";
    }

    @Transactional
    public TakeResponseDto takeFood(String member_id, Long give_id) {
        Long memberId = Long.parseLong(member_id);

        Member taker = findMemberById(memberId);
        Give item = giveRepository.findGiveById(give_id).get();

        if(item.getIsReserved()) throw new OnReservedFoodException();
        if(takeRepository.findTakesByTakerAndIsDone(taker, false).size() >= 2) throw new TooManyReservedException();
        if(!taker.getIsTaker()) throw new NotTakerException();
        if(item.getGiver() == taker) throw new SameGiverTakerException();

        item.setIsReserved(true);
        LocalDateTime currentTime = LocalDateTime.now();

        String noticeMessage = "예약 중인 " + item.getFood().getName() + " 이 있습니다.";
        Notification notification = new Notification(taker, noticeMessage,"reserve", currentTime, false);
        notificationService.makeNotice(notification);

        Take take = new Take(currentTime, taker, item);
        taker.getTakeList().add(take);

        takeRepository.save(take);
        return new TakeResponseDto(take);
    }
    @Transactional
    public String checkTake(Long memberId, Long takeId){
        Take take = findTakeById(takeId);
        if(take.isPending() && takeBelongsToMember(take, memberId)){
            updateTakeStatus(take, Take.Status.IN_PROGRESS);
            return takeId.toString() + " IN PROGRESS!";
        }
        else throw new RuntimeException();
    }

    public Integer numTakesNotDone(String member_id){
        Long memberId = Long.parseLong(member_id);
        Member taker = findMemberById(memberId);

        List<Take> takeListNotDone = takeRepository.findTakesByTakerAndIsDone(taker, false);
        return takeListNotDone.size();
    }

    public List<TakeDtoV2> getReservation(Long takerId) {
        Member taker = findMemberById(takerId);
        List<Take> takes = findTakesByTaker(taker);
        List<Take> reservations = takes.stream().filter(take -> take.isPending()).toList();
        return TakeDtoV2.of(reservations);
    }

    public List<TakeDtoV2> getTakes(Long takerId){
        Member taker = findMemberById(takerId);
        List<Take> takes = findTakesByTaker(taker);
        List<Take> takes2 = takes.stream().filter(take -> !take.isPending()).toList();
        return TakeDtoV2.of(takes2);
    }

    private boolean takeBelongsToMember(Take take, Long memberId) {
        return take.getTaker().getId().equals(memberId);
    }

    private void updateTakeStatus(Take take, Take.Status status) {
        take.setStatus(status);
        takeRepository.save(take);
    }
    private Take findTakeById(Long takeId) {
        return takeRepository.findTakeById(takeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid take id: " + takeId));
    }
    private List<Take> findTakesByTaker(Member taker){
        return takeRepository.findTakesByTaker(taker);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member id: " + memberId));
    }
}
