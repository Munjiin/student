package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BoardVO;
import domain.PageDTO;

public class BoardDAO {
	
	//QuestionDAO
		private String preFix =  "mapper.boardMapper";
		
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
		
		
				
		
		public List<BoardVO> getList(PageDTO dto) {
			System.out.println("--------------------------------------------------d");
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("page",2);
			paramMap.put("size", 20);
			
			System.out.println("--------------------------------------------1");
			try(SqlSession session = sqlSessionFactory.openSession(true)){
				System.out.println("--------------------------------------------2");
				System.out.println(session.selectList(preFix + ".selectPage", paramMap));
				return session.selectList(preFix + ".selectPage", paramMap);
			}catch(Exception e) {
				e.printStackTrace();
		}
			System.out.println("--------------------------------------------3");
			return null;
		}

}
