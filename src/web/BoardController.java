package web;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.imgscalr.Scalr;
import web.Extchecker;

import dao.BoardDAO;
import domain.BoardVO;
import domain.PageDTO;
import domain.PageMaker;
import web.util.Converter;

@WebServlet(urlPatterns = "/user/board/*")

@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100,
        location="C:\\zzz\\upload\\")   
public class BoardController extends AbstractController {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */public BoardController() {
         super();
         // TODO Auto-generated constructor stub
     }
  

	BoardDAO dao = new BoardDAO();
	Converter converter = new Converter();
	
	
	

	public BoardController(BoardDAO dao, Converter converter) {
		super();
		this.dao = dao;
		this.converter = converter;
	}

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
	

	public String writePOST(HttpServletRequest req, HttpServletResponse resp) throws Exception, ServletException, IOException {
		
		
		System.out.println("writePOST..................");

		req.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();

		
		String title = req.getParameter("title");
		String writer = req.getParameter("name");
		String cnt = req.getParameter("cnt");
		String addfile =  UUID.randomUUID()+"_"+req.getParameter("addfile");
		

		vo.setTitle(title);
		vo.setCnt(cnt);
		vo.setName(writer);
		vo.setAddfile(addfile);

		dao.postWrite(vo);
		
	
	
	
	

		//첨부파일
		Collection<Part> parts = req.getParts();
		System.out.println("콜렉션 확인용~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		parts.stream().filter(part->part.getContentType() !=null).forEach(part ->{ //null 인 것만 filter
			
			
			
			System.out.println(part.getContentType());
			System.out.println(part.getSubmittedFileName());
			System.out.println("-----------------------------------------");
			
			String uploadName =part.getSubmittedFileName(); //,거의 중복되지 않는 숫자.
			
			//서버만
			try {
				InputStream in  =  part.getInputStream();
				FileOutputStream fos = new FileOutputStream("C:\\zzz\\upload\\" +  uploadName);
				
				IOUtils.copy(in, fos); //유틸 추가
				
//				if(Extchecker.check(uploadName)) { //확장자 체크
//				
//				//make 섬네일
//				BufferedImage bImage = ImageIO.read(new FileInputStream("C:\\zzz\\upload\\" +  uploadName)); 
//				//resize to 150 pixels max
//				BufferedImage thumbnail = Scalr.resize(bImage,
//                        Scalr.Method.SPEED,
//                        Scalr.Mode.FIT_TO_WIDTH,
//                        150,
//                        100,
//                        Scalr.OP_ANTIALIAS);
//				
//				FileOutputStream thfos = new FileOutputStream("C:\\zzz\\upload\\s_"+uploadName);
//				ImageIO.write(thumbnail, "jpg",thfos );
//				thfos.close();	
//				}//if
				
				in.close();
				fos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		int page = Converter.getInt(req.getParameter("page"), -1);
		req.setAttribute("page", page);
	
		
		
		

		return "redirect/list";
	}

	public String readGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("readGET...........................");

		String bnoStr = req.getParameter("bno");
		int bno = Converter.getInt(bnoStr, -1);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~bno" + bno);

		req.setAttribute("board", dao.getBoard(bno));
		return "read";
	}

	public String modifyGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("modify..............................");

		String bnoStr = req.getParameter("bno");
		int bno = Converter.getInt(bnoStr, -1);

		req.setAttribute("board", dao.getBoard(bno));

		return "modify";
	}

	public void modifyPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");

		System.out.println("modify post..............................");

		BoardVO vo = new BoardVO();
		int bno = converter.getInt(req.getParameter("bno"), -1);

		vo.setBno(bno);
		vo.setTitle(req.getParameter("title"));
		vo.setCnt(req.getParameter("cnt"));

		dao.modifyContent(vo);

		req.setAttribute("board", dao.getBoard(bno));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/read.jsp");
		dispatcher.forward(req, resp);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String removePOST(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("remove................................");

		int bno = Converter.getInt(req.getParameter("bno"), -1);
		int page = Converter.getInt(req.getParameter("page"), -1);

		dao.removeContent(bno);

		return "redirect/list";

	}

	public String getBasic() {
		return "/user/board/";
	}

}
