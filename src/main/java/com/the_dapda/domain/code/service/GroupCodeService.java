package com.the_dapda.domain.code.service;


import com.the_dapda.domain.code.dto.CodeResultDto;
import com.the_dapda.domain.code.entity.GroupCode;

public interface GroupCodeService {
	CodeResultDto insertGroupCode(GroupCode groupCode);
	CodeResultDto updateGroupCode(GroupCode groupCode);
	CodeResultDto deleteGroupCode(String groupCode);	
	
	CodeResultDto listGroupCode(int pageNumber, int pageSize);
	CodeResultDto countGroupCode();
	CodeResultDto detailGroupCode(String groupCode);
}
