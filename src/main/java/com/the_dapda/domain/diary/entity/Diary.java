package com.the_dapda.domain.diary.entity;

import com.the_dapda.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String content;

    private String question;

    @Lob
    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    public Diary(String content, String question, String answer) {
        this.content = content;
        this.question = question;
        this.answer = answer;
    }
}