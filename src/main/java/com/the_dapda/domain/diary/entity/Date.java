package com.the_dapda.domain.diary.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Embeddable
@AllArgsConstructor
public class Date {

    private int year;
    private int month;
    private int day;

    protected Date() {}
}
