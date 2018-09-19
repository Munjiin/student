package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import domain.BoardVO;
import domain.PageDTO;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import dao.BoardDAO;

public class UnderstandDAO {
	
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
	
	
	//BoardDAO
	private String preFix =  "mapper.understandMapper";		
	
	public void create(BoardDAO vo) {
		
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			session.insert(preFix + ".crate", vo);
		}catch(Exception e) {
			e.printStackTrace();
	}
	}

	
	
	
	
	
	
}
	

	
		
	


