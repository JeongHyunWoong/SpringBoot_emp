package com.dw.emp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.emp.service.DeptService;
import com.dw.emp.vo.DeptVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin

public class DeptController {

	@Autowired
	private DeptService service;

	// 전체사원조회
	// PageInfo: 페이징 처리 도와주는 라이브러리!
	// 쿼리스트링으로 수정 ?page=3

	@GetMapping("/dept")
	public PageInfo<Map<String, Object>> callDeptPage(@RequestParam int page) {
		List<Map<String, Object>> list = service.getDeptPageList(page);
		int navigatePages = 5;// 한블록에 보여줄 페이지 수(ex 네이버 웹툰은 1블록에 10페이지)
		return new PageInfo<Map<String, Object>>(list, navigatePages);
	}

	// 부서추가
	@PostMapping("/dept")
	public int callDept(@RequestBody DeptVO vo) {
		return service.setDept(vo);
	}

	// 특정부서조회
	@GetMapping("/dept/deptno/{deptno}")
	public DeptVO callDeptByDeptno(@PathVariable int deptno) {
		return service.getDeptByDeptno(deptno);
	}

	// 특정부서수정
	@PatchMapping("/dept")
	public int callDeptUpdate(@RequestBody DeptVO vo) {
		return service.getDeptUpdate(vo);
	}

	// 부서회원탈퇴 여부 수정 (body가 아니라 path로 데이터를 받자)
	@PatchMapping("/dept/deptno/{deptno}")
	public int callDeptUseUpdate(@PathVariable int deptno) {
		return service.getfireDept(deptno);
	}
	
	//부서 삭제
	@DeleteMapping("/dept/deptno/{deptno}")
	public int callDelDept(@PathVariable int deptno) {
		return service.getDelDept(deptno);
	}
}
