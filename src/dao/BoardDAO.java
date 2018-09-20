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

		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			session.selectList(preFix + ".create", paramMap);

			//return session.selectList(preFix + ".create", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	 public BoardVO getBoard(String bno) throws Exception{
		 
		 
		  BoardVO vo = null;

//	      try (SqlSession session = MyBatisLoader.sqlSessionFactory.openSession(true)) {
//
//	         vo = session.selectOne(prefix + ".read", bno);
//	         
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
//	      return vo;
//	   }
		 

			
			try (SqlSession session = sqlSessionFactory.openSession(true)) {
				
				//System.out.println(session.selectOne(preFix + ".read", bno));
				vo= session.selectOne(preFix + ".read", bno);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return vo;
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		 
//		 
//		 
//		 
//		 
//		 
//	        BoardVO vo = new BoardVO();
//
//	        new QueryExecutor() {
//	            @Override
//	            public void doJob() throws Exception {
//	                if(updateable){
//	                  
//	                    stmt.setInt(1,bno);
//	                    stmt.executeUpdate();
//	                    stmt.close();
//	                }
//	                stmt = con.prepareStatement(READ);
//	                stmt.setInt(1,bno);
//	                rs = stmt.executeQuery();
//	                while(rs.next()){
//	                    vo.setBno(rs.getInt("bno"));
//	                    vo.setTitle(rs.getString("title"));
//	                    vo.setCnt(rs.getString("cnt"));
//	                   
//	                    vo.setName(rs.getString("name"));
//	                    vo.setRegdate(rs.getDate("regdate"));
//	                    vo.setUpdatedate(rs.getDate("updatedate"));
//
//	                }
//	            }
//	        }.executeAll();

	        
	    }

}
