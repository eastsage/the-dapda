package com.the_dapda.domain.diary.service.command;

import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiarySaveResponse;

public interface DiaryCommandService {

    DiarySaveResponse saveDiary(DiarySaveRequest saveRequest);
}
