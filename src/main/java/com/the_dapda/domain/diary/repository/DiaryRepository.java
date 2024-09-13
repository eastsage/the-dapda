package com.the_dapda.domain.diary.repository;

import com.the_dapda.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("select d from Diary d where d.date.year = :year and d.date.month = :month and d.user.userId = :userId ORDER BY d.date.day")
    List<Diary> getMainDiaries(@Param("year") int year, @Param("month") int month, @Param("userId") int userId);
}