package com.dw.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.emp.mapper.EmpMapper;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageHelper;

//@Service : 비즈니스 계층
@Service
public class EmpService {

	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> getEmpList(int deptno){
		/*
		 * 1.사원들 comm 10%인상 후 사원 조회(update => select)
		 * 2.가장 보너스를 많이 받은사람 delete하기
		 */
		int rows = mapper.updateEmpComm(deptno);
		if(rows >0) { //update가 완료됨
			List<EmpVO> list = mapper.selectEmpByDeptno(deptno);
			//가장 급여가 높은사람 empno 찾아내기
			
			int sal = 0; //급여
			
			List<Integer> empNoList = new ArrayList<Integer>();//급여 많이 받는 사원 list 
			for(EmpVO vo : list) {
				if(sal < vo.getSal()) {//급여비교
					sal = vo.getSal();
					empNoList.add(vo.getEmpno());//급여 많이 받는 사원 list에 추가
				}
			}
			for(int empno : empNoList) {
				System.out.println("가장 급여를 많이 받는 사원번호 =>"+empno);
				//mapper.deleteEmp(empno);//사원 삭제
			}
			return list;
		}
		return null; //rows가 0이면 null리턴!
	}
	
	public List<Map<String, Object>> getEmpPageList(int page){
		//필요에 따라 고객 요구사항이 들어오면 여기서 로직을 구현하면된다.
		int pageSize = 10;
		PageHelper.startPage(page,pageSize);
		return mapper.selectEmp();
	}
	//사원 통계 조회
	public Map<String, Object> getEmpStatistics(){
		return mapper.selectEmpStatistics();
	}
	//사원 저장
	public int setEmp(EmpVO vo){
		//이미 가입된 사원번호인지 체크하기
		int empno = vo.getEmpno(); //사원번호를 불러온다.
		int count = mapper.selectCountByEmpno(empno);
		if(count > 0) { //만약에 count가 1이면 이미 가입된 사원
			return 0;
		}
		return mapper.insertEmp(vo);
	}
	//특정사원조회
	public EmpVO getEmpByEmpno(int empno) {
		return mapper.selectEmpByEmpno(empno);
	}
	//특정사원정보수정
	public int getEmpUpdate(EmpVO vo) {
		return mapper.updateEmp(vo);
	}
	//특정사원회원탈퇴(삭제), 컬럼(is_use)만 수정
	public int getfireEmp(int empno) {
		return mapper.fireEmp(empno);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
