package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BoardVO;
import domain.PageDTO;
import domain.QuestionVO;

public class BoardDAO {
	
	//QuestionDAO
		private String preFix =  "mapper.questiondMapper";
		
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
			
			try(SqlSession session = sqlSessionFactory.openSession(true)){
				return session.selectList(preFix + ".selectPage", dto.getPage());
			}catch(Exception e) {
				e.printStackTrace();
		}
			return null;
		}

}
