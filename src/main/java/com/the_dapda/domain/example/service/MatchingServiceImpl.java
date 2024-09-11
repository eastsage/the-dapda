package com.the_dapda.domain.example.service;


import com.the_dapda.domain.example.converter.MatchingListToDto;
import com.the_dapda.domain.example.dto.BannerListResponse;
import com.the_dapda.domain.example.entity.Status;
import com.the_dapda.domain.example.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MatchingServiceImpl implements MatchingService {

    private final MatchingRepository matchingRepository;
    private final MatchingListToDto matchingListToDto;

    @Override
    public List<BannerListResponse> getBannerList() {
        // 최적화된 쿼리로 데이터를 가져옴
        List<Object[]> results = matchingRepository.findMemberForBanner(Status.ACCEPTED);

        // 데이터를 DTO로 변환하여 반환
        return matchingListToDto.convertToBannerListResponse(results);
    }
}
