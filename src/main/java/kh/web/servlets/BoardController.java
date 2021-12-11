package kh.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.web.dao.BoardDAO;
import kh.web.dto.BoardDTO;



@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		HttpSession session = request.getSession();
		BoardDAO bdao = BoardDAO.getInstance();
		
		
		try {
			// 게시판 목록
			if(cmd.equals("/boardList.board")) {
				String cpage = request.getParameter("cpage");
				
				if(cpage == null) {
					cpage = "1";
				}
				
				session.setAttribute("cpage", cpage);
				System.out.println("cpage : " + cpage);
				System.out.println("세션 cpage : " + session.getAttribute("cpage"));
				
				List<BoardDTO> boardList = bdao.selectAll();
				request.setAttribute("boardList", boardList);
				request.getRequestDispatcher("/resources/board/board.jsp?cpage="+cpage).forward(request, response);
			// 글쓰기 기능
			}else if(cmd.equals("/write.board")) {
				// session.removeAttribute("cpage");
				// String cpage = request.getParameter("cpage");
				// session.setAttribute("cpage", cpage);
				// System.out.println("cpage : " + cpage);
				// System.out.println("세션 cpage : " + session.getAttribute("cpage"));
				response.sendRedirect("/resources/board/boardwrite.jsp");
			}else if(cmd.equals("/done.board")) {
				String writer = (String)session.getAttribute("loginID");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = bdao.insert(new BoardDTO(0,writer,title,contents,null,0));
				System.out.println(result);
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
		
		
		
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
