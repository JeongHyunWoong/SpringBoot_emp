package com.dw.emp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.emp.mapper.DeptMapper;
import com.dw.emp.vo.DeptVO;
import com.github.pagehelper.PageHelper;

@Service
public class DeptService {
	@Autowired
	private DeptMapper mapper;
	
	public List<Map<String, Object>> getDeptPageList(int page) {
		// 필요에 따라 고객 요구사항이 들어오면 여기서 로직을 구현하면 됩니다.
		int pageSize = 10; // 한페이지에 보여줄 게시물 수
		PageHelper.startPage(page, pageSize);
		return mapper.selectDept();
	}
	
	// 사원저장
	public int setDept(DeptVO vo) {
		// 이미 가입된 사원번호인지 체크하기
		int deptno = vo.getDeptno();
		if (mapper.selectCountByDeptno(deptno) > 0) { // 만약에 count가 1이면 이미 있는 부서
			return 0;
		}
		return mapper.insertDept(vo);
	}

	// 특정 사원 조회
	public DeptVO getDeptByDeptno(int deptno) {
		return mapper.selectDeptByDeptno(deptno);
	}

	// 특정 사원 정보 수정
	public int getDeptUpdate(DeptVO vo) {
		return mapper.updateDept(vo);
	}

	// 특정 사원 정보 탈퇴(삭제) : 컬럼(is_use)만 수정
	public int getfireDept(int deptno) {
		return mapper.fireDept(deptno);
	}
	
	// 부서 삭제
	public int getDelDept(int deptno) {
		return mapper.deleteDept(deptno);
	}
}
