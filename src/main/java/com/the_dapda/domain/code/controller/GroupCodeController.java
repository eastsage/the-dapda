package com.the_dapda.domain.code.controller;

import com.the_dapda.domain.code.dto.CodeResultDto;
import com.the_dapda.domain.code.entity.GroupCode;
import com.the_dapda.domain.code.service.GroupCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

// 프로젝트의 복잡도를 줄이기 위해 Controller의 리턴 타입이 Entity 이다. 나중에 Dto 로 변경한다.
@RestController
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class GroupCodeController {
	
	private final GroupCodeService groupCodeService;
		
	@GetMapping("/groupcodes")
	public CodeResultDto listGroupCode(
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("pageSize") int pageSize
			) {
		return groupCodeService.listGroupCode( pageNumber, pageSize);
	}	
	
	@GetMapping("/groupcodes/{groupCode}")
	public CodeResultDto detailGroupCode(@PathVariable("groupCode") String groupCode	) {
        return groupCodeService.detailGroupCode(groupCode);
	}
	
	@PostMapping("/groupcodes")
	public CodeResultDto insertGroupCode(GroupCode groupCode){
		System.out.println(groupCode);
		return groupCodeService.insertGroupCode(groupCode);
	}
	
	@PutMapping("/groupcodes")
	public CodeResultDto updateGroupCode(GroupCode groupCode){
		return groupCodeService.updateGroupCode(groupCode);
	}
	
	@DeleteMapping("/groupcodes/{groupCode}")
	public CodeResultDto deleteGroupCode(@PathVariable("groupCode") String groupCode){
		return groupCodeService.deleteGroupCode(groupCode);
	}
}
