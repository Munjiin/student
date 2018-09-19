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

	//qlist
	 public String qlistGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	        PageDTO dto = PageDTO.of()
	                .setPage(Converter.getInt(req.getParameter("page"), 1))
	                .setSize(Converter.getInt(req.getParameter("size"), 10));

	        int total = 320;
	        PageMaker pageMaker = new PageMaker(total, dto);

	        req.setAttribute("pageMaker", pageMaker);
	        req.setAttribute("qlist", dao.getList(dto));

	        return "qlist";
	    }

	 //response
	    public String responseGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	        String qnoStr = req.getParameter("qno");
	        int qno = Converter.getInt(qnoStr, -1);
	        String pageStr = req.getParameter("page");
	        int page = Converter.getInt(pageStr, -1);
	        boolean updateable = false;

	        if (qno == -1) {
	            throw new Exception("invalid data");
	        }

	

	        return "response";
	    }
	   

	    public String getBasic() {
	        return "/user/question/";
	    }
}
