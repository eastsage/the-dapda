package com.the_dapda.domain.code.entity.key;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@AllArgsConstructor 로 처리하려면 serialVersionUID 제거해야 그렇지 않으면 별도의 생성자 추가
@NoArgsConstructor
@Embeddable
public class CodeKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String groupCode;
    private String code;
    
	public CodeKey(String groupCode, String code) {
		this.groupCode = groupCode;
		this.code = code;
	}
}
