package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAO;
import domain.PageDTO;
import domain.PageMaker;
import web.util.Converter;

@WebServlet(urlPatterns = "/user/question/*")
public class QuestionController extends AbstractController{

	QuestionDAO dao = new QuestionDAO();

	//list
	 public String qlistGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		 	System.out.println("-------------------------------------1");
	        PageDTO dto = PageDTO.of()
	                .setPage(Converter.getInt(req.getParameter("page"), 1))
	                .setSize(Converter.getInt(req.getParameter("size"), 10));

	        int total = 320;
	        PageMaker pageMaker = new PageMaker(total, dto);

	        req.setAttribute("pageMaker", pageMaker);
	        req.setAttribute("qlist", dao.getList(dto));
	        System.out.println("¸®½ºÆ®: " + dao.getList(dto));

	        return "qlist";
	    }

	 //read
	 
/*	    public String readGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	        String bnoStr = req.getParameter("bno");
	        int bno = Converter.getInt(bnoStr, -1);
	        String pageStr = req.getParameter("page");
	        int page = Converter.getInt(pageStr, -1);
	        boolean updateable = false;

	        if (bno == -1) {
	            throw new Exception("invalid data");
	        }

	

	        return "read";
	    }*/

	    public String getBasic() {
	        return "/user/question/";
	    }
}
