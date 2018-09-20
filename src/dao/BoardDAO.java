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

	// QuestionDAO
	private String preFix = "mapper.boardMapper";

	// MybatisLoader
	static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> getList(PageDTO dto) {
		System.out.println("--------------------------------------------------d");

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("page", dto.getPage());
		paramMap.put("size", dto.getSize());

		System.out.println("--------------------------------------------1");
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("--------------------------------------------2");
			System.out.println(session.selectList(preFix + ".selectPage", paramMap));
			return session.selectList(preFix + ".selectPage", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------3");
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void postWrite(BoardVO vo) throws Exception {

//		        new QueryExecutor() {
//		            @Override
//		            public void doJob() throws Exception {
//		                stmt = con.prepareStatement(INSERT);
//		                int i = 1;
//		                stmt.setString(i++, vo.getTitle());
//		                stmt.setString(i++, vo.getCnt());
//		                stmt.setString(i++, vo.getName());
//		                stmt.executeUpdate();
//		            }
//		        }.executeAll();

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", vo.getTitle());
		paramMap.put("name", vo.getName());
		paramMap.put("cnt", vo.getCnt());

		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.selectList(preFix + ".create", paramMap);

			//return session.selectList(preFix + ".create", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
