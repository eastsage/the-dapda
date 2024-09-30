package com.the_dapda.domain.code.service;


import com.the_dapda.domain.code.dto.CodeResultDto;
import com.the_dapda.domain.code.entity.Code;
import com.the_dapda.domain.code.entity.key.CodeKey;

public interface CodeService {
	CodeResultDto insertCode(Code code);
	CodeResultDto updateCode(Code code);
	CodeResultDto deleteCode(CodeKey codeKey);
	
	CodeResultDto listCode(String goupCode, int pageNumber, int pageSize);
	CodeResultDto countCode();
	CodeResultDto detailCode(CodeKey codeKey);	
}
