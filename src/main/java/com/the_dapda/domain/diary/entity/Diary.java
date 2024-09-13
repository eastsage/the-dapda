package com.the_dapda.domain.diary.entity;

import com.the_dapda.domain.BaseTimeEntity;
import com.the_dapda.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity {

    @Id
    @Column(name = "diary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Date date;

    private String content;

    private String question;

    @Lob
    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Diary(Date date, String content, String question, String answer, User user) {
        this.date = date;
        this.content = content;
        this.question = question;
        this.answer = answer;
        this.user = user;
    }
}