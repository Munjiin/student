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

	// list
	public String listGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		PageDTO dto = PageDTO.of().setPage(Converter.getInt(req.getParameter("page"), 1))
				.setSize(Converter.getInt(req.getParameter("size"), 10));

		int total = 320;
		PageMaker pageMaker = new PageMaker(total, dto);

		System.out.println("-----------------------------------------a");

		req.setAttribute("pageMaker", pageMaker);
		System.out.println("-----------------------------------------------b");
		req.setAttribute("selectPage", dao.getList(dto));

		System.out.println("-------------------------------------------c");
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

	public String getBasic() {
		return "/user/board/";
	}

	/*
	 * 
	 * BoardDAO dao = new BoardDAO();
	 * 
	 * 
	 * 
	 * 
	 * public String listGET(HttpServletRequest req, HttpServletResponse resp)
	 * throws Exception{ System.out.println("listGET...........................");
	 * 
	 * PageDTO dto = PageDTO.of()
	 * .setPage(Converter.getInt(req.getParameter("page"),1))
	 * .setSize(Converter.getInt(req.getParameter("size"),10));
	 * 
	 * 
	 * 
	 * int total = 320; PageMaker pageMaker = new PageMaker(total,dto);
	 * 
	 * req.setAttribute("pageMaker",pageMaker);
	 * req.setAttribute("list",dao.getList(dto));
	 * 
	 * return "list"; }
	 * 
	 * 
	 * 
	 * 
	 * @Override public String getBasic() {
	 * 
	 * return "/board/"; }
	 */
}
