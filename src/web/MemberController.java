package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import web.util.Converter;

@WebServlet(urlPatterns = "/user/login/*")
public class MemberController extends AbstractController {

	MemberDAO dao = new MemberDAO();

	public String signupGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "signup";
	}

	public String signupPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("---------------------------post µé¾î¿È");
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String id = req.getParameter("id");
		int password = Converter.getInt((req.getParameter("password")), -1);
		int seatnum = Converter.getInt((req.getParameter("seatnum")), -1);

		System.out.println("--------------name: " + name);
		System.out.println("--------------id: " + id);
		System.out.println("--------------pass: " + password);
		System.out.println("--------------seatnum: " + seatnum);

		dao.signUp(name, id, password, seatnum);

		return "redirect:/user/login/";
	}

	public String getBasic() {
		return "/user/login/";
	}
}
