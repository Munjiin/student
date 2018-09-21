package dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.MemberVO;
import domain.PageDTO;
import domain.QuestionVO;

public class MemberDAO {
	private String preFix =  "mapper.memberMapper";
	Map<String, Object> paramMap = new HashMap<>();
	
	//MybatisLoader
	static SqlSessionFactory sqlSessionFactory;

	static{
		try {
			String resource = "mybatis-config.xml";
	         InputStream inputStream = Resources.getResourceAsStream(resource);
	          sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	//회원가입
	public void signUp(String name, String id, int password, int seatnum) {
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		vo.setSeatnum(seatnum);
		
	System.out.println("-------------------------------가입");
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.insert(preFix + ".signUp", vo);


		} catch (Exception e) {
			e.printStackTrace();

		}
	}	
	
	
		

	
	
}
	