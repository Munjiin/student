package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import domain.MemberVO;
import web.util.Converter;

@WebServlet(urlPatterns = "/user/login/*")
public class MemberController extends AbstractController {

	MemberDAO dao = new MemberDAO();

	public String signupGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "signup";
	}

	public String signupPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String id = req.getParameter("id");
		int password = Converter.getInt((req.getParameter("password")), -1);
		int seatnum = Converter.getInt((req.getParameter("seatnum")), -1);
		
		System.out.println("------------id" + id);
		System.out.println(dao.login(id).getId());
		
		if (dao.login(id).getId().equals(id)) {
			return "signup";
		}

		dao.signup(name, id, password, seatnum);

		return "redirect:/";
	}

	public String loginPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");

		String id = req.getParameter("id");
		int password = Converter.getInt((req.getParameter("password")), -1);

		MemberVO vo = dao.login(id);

		if(password==vo.getPassword()) {
			req.getSession().setAttribute("mno", vo.getMno());
			req.setAttribute("mno", vo.getMno());
			return "redirect:/user/question/qlist";
			
		}else {
			return "redirect:/";
		}

	}

	
	
	public String getBasic() {
		return "/user/login/";
	}
}
