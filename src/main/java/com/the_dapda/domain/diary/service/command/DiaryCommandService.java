package com.the_dapda.domain.diary.service.command;

import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiaryDeleteResponse;
import com.the_dapda.domain.user.entity.User;

public interface DiaryCommandService {

    Long saveDiary(DiarySaveRequest saveRequest, User user);

    DiaryDeleteResponse deleteDiary(Long diaryId);
}