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
import kh.web.statics.BoardStatics;



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
		
		// cpage 세션에 저장
		session.setAttribute("cpage", request.getParameter("cpage"));
		String cpage = (String)session.getAttribute("cpage");
		
		System.out.println("cpage : " + request.getParameter("cpage"));
		System.out.println("세션 cpage : " + cpage);
		if(cpage == null) {cpage="1";}
		
		try {
			// 게시판 목록
			if(cmd.equals("/boardList.board")) {
				int currentPage = Integer.parseInt(cpage);
				int start = currentPage * BoardStatics.RECORD_COUNT_PER_PAGE-9;
				int end = currentPage * BoardStatics.RECORD_COUNT_PER_PAGE;
				List<BoardDTO> boardList = bdao.selectByBound(start,end);
				String navi = bdao.getPageNavi(currentPage);
				request.setAttribute("navi", navi);
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
			// 글 작성 완료
			}else if(cmd.equals("/done.board")) {
				// String writer = (String)session.getAttribute("loginID");
				String writer = "test";
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = bdao.insert(new BoardDTO(0,writer,title,contents,null,0));
				System.out.println("게시글 insert 결과 : " + result);
				response.sendRedirect("/boardList.board");
			// 게시글 페이지로 이동
			}else if(cmd.equals("/detail.board")) {
				String seq = request.getParameter("seq");
				BoardDTO dto = bdao.selectBySeq(Integer.parseInt(seq));
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/resources/board/boardDetail.jsp?cpage"+cpage+"&seq="+seq).forward(request, response);
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
