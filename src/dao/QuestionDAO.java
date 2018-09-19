package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.BoardVO;
import domain.PageDTO;
import domain.QuestionVO;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import dao.BoardDAO;

public class QuestionDAO {
	
	//QuestionDAO
	private String preFix =  "mapper.questionMapper";
	
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
	
	
			
	
	public List<QuestionVO> getList(PageDTO dto) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("page", dto.getPage());
		paramMap.put("size", dto.getSize());
		
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			
			System.out.println("--------------------------------------2");
			System.out.println(session.selectList(preFix + ".qlist", paramMap));
			return session.selectList(preFix + ".qlist", paramMap);
		}catch(Exception e) {
			e.printStackTrace();
	}
		System.out.println("--------------------------------------3");
		return null;
	}

	
	
	
	
	
	
}
	

	
		
	


