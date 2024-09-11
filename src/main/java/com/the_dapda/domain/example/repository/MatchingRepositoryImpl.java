package com.the_dapda.domain.example.repository;

import com.the_dapda.domain.example.entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class MatchingRepositoryImpl implements MatchingRepository {

    private final EntityManager em;

    @Override
    public List<Object[]> findMemberForBanner(Status status) {
        try {
            return em.createQuery("SELECT s.nickname, s.mbti, s.gender, r.nickname, r.mbti, s.gender " +
                            "FROM Matching m " +
                            "JOIN m.sender s " +
                            "JOIN m.receiver r " +
                            "WHERE m.status = :status " +
                            "ORDER BY m.createdAt DESC", Object[].class)
                    .setParameter("status", status)
                    .setMaxResults(10)
                    .getResultList();
        } catch (Exception e) {
            log.error("배너에 적힐 10명의 매칭을 찾는 도중에 에러가 발생했습니다: {}", status, e);
            return List.of(); // 빈 리스트 반환
        }
    }
}
