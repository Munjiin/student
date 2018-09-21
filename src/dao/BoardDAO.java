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

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("page", dto.getPage());
		paramMap.put("size", dto.getSize());

		try (SqlSession session = sqlSessionFactory.openSession(true)) {

			return session.selectList(preFix + ".selectPage", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void postWrite(BoardVO vo) throws Exception {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("title", vo.getTitle());
		paramMap.put("name", vo.getName());
		paramMap.put("cnt", vo.getCnt());
		paramMap.put("addfile", vo.getAddfile());

		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.selectList(preFix + ".create", paramMap);

			// return session.selectList(preFix + ".create", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	public BoardVO getBoard(int bno) throws Exception {

		BoardVO vo = null;

		try (SqlSession session = sqlSessionFactory.openSession(true)) {

			vo = session.selectOne(preFix + ".read", bno);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;

	}

	public void modifyContent(final BoardVO vo) {

		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("bno", vo.getBno());
		paramMap.put("title", vo.getTitle());
		paramMap.put("cnt", vo.getCnt());

		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.selectList(preFix + ".modify", paramMap);

			// return session.selectList(preFix + ".create", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	///////////////////////////////////////////////////////////////////////////////////
	// remove
	public void removeContent(final int bno) {
		
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.selectList(preFix + ".remove", bno);

			// return session.selectList(preFix + ".create", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
