package com.the_dapda.domain.code.dto;

import com.the_dapda.domain.code.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CodeDto {
	private String groupCode;
	private String code;
	private String codeName;
	private String codeNameBrief;
	private int order;

	public static CodeDto fromCode(Code code) {
		return CodeDto.builder()
			.groupCode(code.getCodeKey().getGroupCode())
			.code(code.getCodeKey().getCode())
			.codeName(code.getCodeName())
			.codeNameBrief(code.getCodeNameBrief())
			.order(code.getOrderNo())
			.build();
	}
}
