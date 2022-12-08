package com.dw.emp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.emp.vo.DeptVO;

@Mapper
public interface DeptMapper {

	// 전체부서조회
	public List<Map<String, Object>> selectDept();

	// 부서추가
	public int insertDept(DeptVO vo);

	// 특정부서조회
	public DeptVO selectDeptByDeptno(int deptno);

	// 특정부서수정
	public int updateDept(DeptVO vo);

	// 특정부서삭제
	public int fireDept(int deptno);

	// 이미 가입된 사원인지 아닌지 체크
	public int selectCountByDeptno(int deptno);
	
	// 부서삭제
	public int deleteDept(int deptno);
}
