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
	
	//ȸ������
	public void signup(String name, String id, int password, int seatnum) {
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		vo.setSeatnum(seatnum);
		System.out.println("���Գ���???"+vo);
	System.out.println("-------------------------------����");
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("----------------------------------����");
			session.insert(preFix + ".signup", vo);
			System.out.println("----------------------------------����222");


		} catch (Exception e) {
			e.printStackTrace();

		}
	}	
	
	//�α���
	public MemberVO login(String id) {
		
	System.out.println("---------------------�α��� dao");
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("---------------------�α��� dao2");
			return session.selectOne(preFix + ".login", id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}	
	
		

	
	
}
	