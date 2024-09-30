package com.the_dapda.domain.code.controller;

import com.the_dapda.domain.code.dto.CodeResultDto;
import com.the_dapda.domain.code.entity.Code;
import com.the_dapda.domain.code.entity.key.CodeKey;
import com.the_dapda.domain.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

// 프로젝트의 복잡도를 줄이기 위해 Controller의 리턴 타입이 Entity 이다. 나중에 Dto 로 변경한다.
@RestController
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class CodeController {
	
	private final CodeService codeService;
		
	@GetMapping("/codes")
	public CodeResultDto listCode(
			@RequestParam("groupCode") String groupCode, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("pageSize") int pageSize
			) {
		return codeService.listCode(groupCode, pageNumber, pageSize);
	}	
	
	@GetMapping("/codes/{groupCode}/{code}")
	public CodeResultDto detailCode(
			@PathVariable("groupCode") String groupCode,
			@PathVariable("code") String code
			) {
		CodeKey codeKey = new CodeKey(groupCode, code);
        return codeService.detailCode(codeKey);
	}
	
//	@PostMapping("/codes")
//	public CodeResultDto insertCode(CodeKey codeKey, Code code){
//		System.out.println(code);
//		code.setCodeKey(codeKey);
//		return codeService.insertCode(code);
//	}
	

	@PostMapping("/codes")
	public CodeResultDto insertCode(
			@RequestParam("groupCode") String groupCode, 
			@RequestParam("code") String code, 
			Code codeParam){
		System.out.println(codeParam);
		CodeKey codeKey = new CodeKey(groupCode, code);
		codeParam.setCodeKey(codeKey);
		return codeService.insertCode(codeParam);
	}	

	@PutMapping("/codes")
	public CodeResultDto updateCode(
			@RequestParam("groupCode") String groupCode, 
			@RequestParam("code") String code, 
			Code codeParam){			

		CodeKey codeKey = new CodeKey(groupCode, code);
		codeParam.setCodeKey(codeKey);				
		return codeService.updateCode(codeParam);
	}
	
	@DeleteMapping("/codes/{groupCode}/{code}")
	public CodeResultDto deleteCode(
			@PathVariable("groupCode") String groupCode,
			@PathVariable("code") String code
	){
		CodeKey codeKey = new CodeKey(groupCode, code);
		return codeService.deleteCode(codeKey);
	}
}
