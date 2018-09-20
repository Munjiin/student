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
	private String preFix =  "mapper.questionMapper";
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
	
		
	//Question
	public List<QuestionVO> getList(PageDTO dto) {
		
		paramMap.put("page", dto.getPage());
		paramMap.put("size", dto.getSize());
		
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			
			return session.selectList(preFix + ".qlist", paramMap);
		}catch(Exception e) {
			e.printStackTrace();
	}
		return null;
	}

	//Response
	public List<QuestionVO> getResponse(int qno, int mno) {
		
		paramMap.put("qno", qno);
		paramMap.put("mno", mno);
		System.out.println("----------------------------------------qno"+qno);
		System.out.println("----------------------------------------mno"+mno);
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			System.out.println("--------------------------------------------1");
			return session.selectList(preFix + ".response", paramMap);

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("--------------------------------------------2");
		return null;
	}
	
	//UnderstandRead
	public List<QuestionVO> getUnderstandRead(int qno) {
		
		paramMap.put("qno", qno);
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.selectList(preFix + ".understandRead", paramMap);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	//UnderstandSend
	public void getUnderstandSend(int qno, int mno, int reply, String cmt) {
		
		QuestionVO vo = new QuestionVO();
		vo.setQno(qno);
		vo.setMno(mno);
		vo.setReply(reply);
		vo.setCmt(cmt);
		
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.insert(preFix + ".understandSend", vo);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}	
	
	//replyCheck
		public Integer getReplyCheck(int qno, int mno) {
		
			paramMap.put("qno", qno);
			paramMap.put("mno", mno);
			
			try (SqlSession session = sqlSessionFactory.openSession(true)) {
				return session.selectOne(preFix + ".replyCheck", paramMap);

			} catch (Exception e) {
				e.printStackTrace();

			}
			return -1;
		}
		
		//Question
		public List<QuestionVO> getQuestion(int qno) {

			try(SqlSession session = sqlSessionFactory.openSession(true)){
				
				return session.selectList(preFix + ".getQuestion", qno);
			}catch(Exception e) {
				e.printStackTrace();
		}
			return null;
		}
	
		
		//totalpage
		public int getPage() {

			try(SqlSession session = sqlSessionFactory.openSession(true)){
				
				return session.selectOne(preFix + ".getPage");
			}catch(Exception e) {
				e.printStackTrace();
		}
			return 0;
		}
	
	
	
}
	

	
		
	


