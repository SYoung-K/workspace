package vCenter;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybaFactory;


public class vCenterDao {
	SqlSession sqlSession;
	
	public vCenterDao() {
		try {
			sqlSession = MybaFactory.getFactory().openSession();
			//MybaFactory 클래스의 getFactory() 메서드를 통하여 SqlSessionFactory객체를 가져오고 이 객체의 openSession()를 호출하여 받은 리턴값을 저장한다.
			
			if(sqlSession == null) {	//sqlSession에 데이터가 있다면 정상 저장됨을 출력
				System.out.println("sqlSession 생성시 오류 발생");
			}else {
				System.out.println("정상적으로 sqlSession이 생성됨");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void insert(vCenterVo vo) {
		int r=0;
		
		try {
			r = sqlSession.insert("vCenter.insert", vo); 
			if(r>0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}		
	}

public List sido() {
	List list = null;
	
	try {
		list = sqlSession.selectList("vCenter.sido"); 
		
		if(list.size()>0) {	
			
		}else {
			sqlSession.rollback();
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}

	return list;
}
	
public List sigungu(String str) {
	List list = null;
	try {
		list = sqlSession.selectList("vCenter.sigungu", str); 
		
		if(list.size()>0) {
			
		}else {
			sqlSession.rollback();
		}
		
	}catch(Exception ex) {
		ex.printStackTrace();
		
	}		
	return list;
}

public List dong(vCenterVo vo) {
	List list = new ArrayList<vCenterVo>();
	try {
		list = sqlSession.selectList("vCenter.dong", vo); 
		
		if(list.size()>0) {

		}else {
			sqlSession.rollback();
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}		
	return list;
}

public List<vCenterVo> search(vCenterVo mv) {
	List<vCenterVo> list = new ArrayList<vCenterVo>();
	try {
		list = sqlSession.selectList("vCenter.search", mv); 
		
		if(list.size()>0) {
	
		}else {
			sqlSession.rollback();
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}		
	return list;
}

public vCenterVo information(vCenterVo mv) {
	vCenterVo vo = null;
	try {
		vo = sqlSession.selectOne("vCenter.information", mv); 
		
		if(vo!=null) {
		
		}else {
			sqlSession.rollback();
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}		
	return vo;
}
		
	
}