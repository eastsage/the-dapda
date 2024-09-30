package com.the_dapda.domain.code.entity;

import com.the_dapda.domain.code.entity.key.CodeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Code {

	@EmbeddedId
	CodeKey codeKey;
	
	@Column(name = "code_name")
	private String codeName;
	
	@Column(name = "code_name_brief")
	private String codeNameBrief;
	
	@Column(name = "order_no")
	private int orderNo; // order 로 하면 예약어로 오류 발생
}
