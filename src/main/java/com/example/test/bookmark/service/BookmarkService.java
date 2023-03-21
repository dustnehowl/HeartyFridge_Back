package com.example.test.bookmark.service;

import com.example.test.bookmark.Bookmark;
import com.example.test.bookmark.controller.dto.BookmarkResponse;
import com.example.test.bookmark.repository.BookmarkRepository;
import com.example.test.exception.BookmarkNotFoundException;
import com.example.test.exception.DuplicateBookmarkException;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final FridgeRepository fridgeRepository;

    public String test() {
        return "OK";
    }

    public BookmarkResponse addBookmark(Long memberId, Long fridgeId) {
        Member member = memberRepository.findMemberById(memberId).get();
        Fridge fridge = fridgeRepository.findFridgeById(fridgeId).get();

        Optional<Bookmark> bookmarkByMemberAndFridge = bookmarkRepository.findBookmarkByMemberAndFridge(member, fridge);
        if(bookmarkByMemberAndFridge.isPresent()) throw new DuplicateBookmarkException();

        Bookmark bookmark = Bookmark.builder()
                .fridge(fridge)
                .member(member)
                .build();
        bookmarkRepository.save(bookmark);

        return BookmarkResponse.from(bookmark);
    }

    public String delBookmark(Long memberId, Long fridgeId) {
        Member member = memberRepository.findMemberById(memberId).get();
        Fridge fridge = fridgeRepository.findFridgeById(fridgeId).get();

        Optional<Bookmark> bookmarkByMemberAndFridge = bookmarkRepository.findBookmarkByMemberAndFridge(member, fridge);

        if(bookmarkByMemberAndFridge.isEmpty()) throw new BookmarkNotFoundException();

        bookmarkRepository.delete(bookmarkByMemberAndFridge.get());
        return "Delete complete";
    }
}
