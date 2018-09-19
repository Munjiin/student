package web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;

import dao.BoardDAO;
import domain.PageDTO;
import domain.PageMaker;
import web.util.Converter;

@WebServlet(urlPatterns="/user/*")
public class BoardController extends AbstractController {

	BoardDAO dao = new BoardDAO();
	
	
	 public String listGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	        System.out.println("listGET...........................");
	    
			
			

	        PageDTO dto = PageDTO.of()
	                .setPage(Converter.getInt(req.getParameter("page"),1))
	                .setSize(Converter.getInt(req.getParameter("size"),10));

	        int total = 320;
	        PageMaker pageMaker = new PageMaker(total,dto);

	        req.setAttribute("pageMaker",pageMaker);
	        req.setAttribute("list",dao.getList(dto));

	        return "list";
	    }

	 
	 
	 
	@Override
	public String getBasic() {
		
		return "/board/";
	}
}
