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
	private String preFix = "mapper.memberMapper";
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
	public void signup(String name, String id, int password, int seatnum) {
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		vo.setSeatnum(seatnum);
		System.out.println("들어왔나요???"+vo);
	System.out.println("-------------------------------가입");
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("----------------------------------과연");
			session.insert(preFix + ".signup", vo);
			System.out.println("----------------------------------과연222");


		} catch (Exception e) {
			e.printStackTrace();

		}
	}	
	
	//로그인
	public MemberVO login(String id) {
		
	System.out.println("---------------------로그인 dao");
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("---------------------로그인 dao2");
			return session.selectOne(preFix + ".login", id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}	
	
		

	
	
}
	