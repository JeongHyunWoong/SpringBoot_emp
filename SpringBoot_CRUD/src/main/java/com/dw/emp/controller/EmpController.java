package com.dw.emp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.emp.mapper.EmpMapper;
import com.dw.emp.service.EmpService;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageInfo;

@RestController
//@RequestMapping : 전역변수 개념
@RequestMapping("/api/v1")
@CrossOrigin
public class EmpController {
	
	/*
	 * M(Model == Mapper&Service) V(View) C(Controller)는
	 * 규모가 큰 프로젝트에서 업무분담을 하기위해 사용하는 적절한 디자인 패턴(==코드 아키텍쳐) 중 하나.
	 * 장점 : 유지보수쉽다, 직관적이다, 인력채용쉽다\
	 * 단점 : 프로젝트 규모가 작다면 시간낭비, 가독성 떨어짐
	 * point : MVC는 Spring뿐만아니라 여러 프레임워크에서 사용하는 디자인 패턴이다!
	 * 		   MVC는 Spring 기술이 절대아님!!
	 */
	//@Autowired = new 자동 생성(Spring이 클래스를 관리해준다. == )
	//프레임워크(Spring boot)가 클래스를 관리해주는 기술을 Dependency Injection(DI)라고 표현한다.
	@Autowired
	private EmpService service;
	
	@GetMapping("/emp/deptno/{deptno}")
	public List <EmpVO> callEmp(@PathVariable int deptno){
		
		return service.getEmpList(deptno);
	}
	//전체사원조회
	//쿼리스트링으로 수정 ?page=3
	@GetMapping("/emp")
	public PageInfo<Map<String, Object>> callEmpPage(@RequestParam int page){
		
		List<Map<String, Object>> list = service.getEmpPageList(page);
		int navigatePages = 5;
		return new PageInfo<Map<String, Object>>(list,navigatePages);
	}
	//사원정보 통계 조회
	@GetMapping("/emp/statistics")
	public Map<String, Object> callStatistics(){
		return service.getEmpStatistics();
	}
	
	//사원 추가
	@PostMapping("/emp")
	public int callEmp(@RequestBody EmpVO vo) {
		return service.setEmp(vo);
	}
	
	//특정사원조회
	@GetMapping("/emp/empno/{empno}")
	public EmpVO callEmpByEmpno(@PathVariable int empno) {
		return service.getEmpByEmpno(empno);
	}
	
	//특정사원정보수정
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO vo) {
		return service.getEmpUpdate(vo);
	}
	
	//특정 회원탈퇴 여부 수정 (body가 아니라  
	@PatchMapping("/emp/empno/{empno}")
	public int callEmpUseUpdate(@PathVariable int empno) {
		return service.getfireEmp(empno);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
