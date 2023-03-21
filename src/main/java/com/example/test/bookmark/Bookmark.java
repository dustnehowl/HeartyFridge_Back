package com.example.test.bookmark;

import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@AllArgsConstructor
@Getter
public class Bookmark {
    @Id @Column(name = "BOOKMARK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "fridge_id")
    private Fridge fridge;

    public Bookmark() {}

    @Builder
    public Bookmark(Member member, Fridge fridge) {
        this.member = member;
        this.fridge = fridge;
    }
}
