package com.the_dapda.domain.example.service;


import com.the_dapda.domain.example.dto.BannerListResponse;

import java.util.List;

public interface MatchingService {
    List<BannerListResponse> getBannerList();
}
