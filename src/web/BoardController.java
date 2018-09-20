package web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BoardVO;

import dao.BoardDAO;
import dao.QuestionDAO;
import domain.PageDTO;
import domain.PageMaker;
import web.util.Converter;

@WebServlet(urlPatterns = "/user/board/*")
public class BoardController extends AbstractController {

	BoardDAO dao = new BoardDAO(); 
	Converter converter = new Converter();

	// list
	public String listGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		PageDTO dto = PageDTO.of().setPage(Converter.getInt(req.getParameter("page"), 1))
				.setSize(Converter.getInt(req.getParameter("size"), 10));

		int total = 320;
		PageMaker pageMaker = new PageMaker(total, dto);

	

		req.setAttribute("pageMaker", pageMaker);
		
		req.setAttribute("selectPage", dao.getList(dto));

	
		return "list";
	}

	public String writeGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("writeGET...........................");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/write.jsp");

		dispatcher.forward(req, resp);
		return "write";
	}
	
	public String writePOST (HttpServletRequest req, HttpServletResponse resp) throws Exception{
	      System.out.println("writePOST..................");
	      
	      req.setCharacterEncoding("UTF-8");
	      BoardVO vo = new BoardVO();
	      
	      String title = req.getParameter("title");
	      String writer = req.getParameter("name");
	      String cnt = req.getParameter("cnt");
	      
	      vo.setTitle(title);
	      vo.setName(writer);
	      vo.setCnt(cnt);
	      
	      dao.postWrite(vo);
	      
	      int page = Converter.getInt(req.getParameter("page"),-1);
	      req.setAttribute("page",page);
	      
	      
	      return "redirect/list";
	   }
	
	
	public String readGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("readGET...........................");
        
        String bnoStr = req.getParameter("bno");
        int bno = Converter.getInt(bnoStr,-1);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~bno"+bno);
       
           
        req.setAttribute("board",dao.getBoard(bno));
        return "read";
	}
	
	
	 public String modifyGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	        System.out.println("modify..............................");

	        
	        String bnoStr = req.getParameter("bno");
	        int bno = Converter.getInt(bnoStr,-1);
	        
	        req.setAttribute("board",dao.getBoard(bno));

	        return "modify";
	    }

	    public void modifyPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception{

	        req.setCharacterEncoding("UTF-8");

	        System.out.println("modify post..............................");
	       

	        BoardVO vo = new BoardVO();
	        int bno = converter.getInt(req.getParameter("bno"),-1);

	        vo.setBno(bno);
	        vo.setTitle(req.getParameter("title"));
	        vo.setCnt(req.getParameter("cnt"));

	        dao.modifyContent(vo);

	        req.setAttribute("board",dao.getBoard(bno));

	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/read.jsp");
	        dispatcher.forward(req,resp);
	    }
	
	
	

	public String getBasic() {
		return "/user/board/";
	}

	
}
