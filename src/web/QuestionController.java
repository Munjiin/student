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
	        int mno = Converter.getInt((req.getParameter("mno")),-1);
	        

	        if (qno == -1) {
	            throw new Exception("invalid data");
	        }

	        req.setAttribute("response", dao.getResponse(qno, 1));	
	        return "response";
	    }
	   
	    
	    
	    //understandRead
	    
	    public String understandGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	        String qnoStr = req.getParameter("qno");
	        int qno = Converter.getInt(qnoStr, -1);
	        int mno = Converter.getInt((req.getParameter("mno")),-1);
	        String pageStr = req.getParameter("page");
	        int page = Converter.getInt(pageStr, -1);
	        
	        req.setAttribute("question", dao.getUnderstandRead(qno));
	        

	        return "understand";
	    }
	    
	    
    //understandSend
	    
	    public String understandPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	    	req.setCharacterEncoding("UTF-8");

	        String qnoStr = req.getParameter("qno");
	        int qno = Converter.getInt(qnoStr, -1);
	        String pageStr = req.getParameter("page");
	        int page = Converter.getInt(pageStr, -1);
	        int mno = Converter.getInt((req.getParameter("mno")),-1);
	        int reply = Converter.getInt(req.getParameter("reply"),-1);
	        String cmt = req.getParameter("cmt");
	        
	        
	        dao.getUnderstandSend(qno,1,reply,cmt);

	 
	        return "redirect:/user/question/qlist";
	    }
	    
	    
	   
	    public String getBasic() {
	        return "/user/question/";
	    }
	    
}
