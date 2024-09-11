package com.the_dapda.domain.example.repository;


import com.the_dapda.domain.example.entity.Status;

import java.util.List;

public interface MatchingRepository {
    List<Object[]> findMemberForBanner(Status status);
}
