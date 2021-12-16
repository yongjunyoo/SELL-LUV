package kh.web.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.web.dao.BoardDAO;
import kh.web.dao.CommentDAO;
import kh.web.dto.BoardDTO;
import kh.web.dto.CommentDTO;
import kh.web.statics.BoardStatics;



@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------------------");
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		HttpSession session = request.getSession();
		BoardDAO bdao = BoardDAO.getInstance();
		CommentDAO cdao = CommentDAO.getInstance();
		
		// cpage, seq 세션에 저장
		session.setAttribute("cpage", request.getParameter("cpage"));
		String cpage = (String)session.getAttribute("cpage");
		session.setAttribute("seq", request.getParameter("seq"));
		String seq = (String)session.getAttribute("seq");
		
		System.out.println("cpage : " + request.getParameter("cpage"));
		System.out.println("세션 cpage : " + cpage);
		System.out.println("seq : " + request.getParameter("seq"));
		System.out.println("세션 seq : " + seq);
		
		System.out.println("로그인 ID : " + session.getAttribute("loginID"));
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
			// 프로필 사진 읽기
			}else if(cmd.equals("/write.board")) {
				response.sendRedirect("/resources/board/boardwrite.jsp");
			// 글 작성 완료
			}else if(cmd.equals("/done.board")) {
				String writer = (String)session.getAttribute("name");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = bdao.insert(new BoardDTO(0,writer,title,contents,null,0));
				System.out.println("게시글 insert 결과 : " + result);
				response.sendRedirect("/boardList.board?cpage="+cpage);
			// 게시글 페이지로 이동
			}else if(cmd.equals("/detail.board")) {
				BoardDTO dto = bdao.selectBySeq(Integer.parseInt(seq));
				request.setAttribute("dto", dto);
				// 댓글 가져가기
				List<CommentDTO> cList = cdao.selectByBoardSeq(Integer.parseInt(seq));
				request.setAttribute("cList", cList);
				
				// 조회수 올리기
				bdao.addViewCount(Integer.parseInt(seq));
				request.getRequestDispatcher("/resources/board/boardDetail.jsp?cpage"+cpage+"&seq="+seq).forward(request, response);
			// 게시글 수정
			}else if(cmd.equals("/modify.board")) {
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = bdao.modify(Integer.parseInt(seq),title,contents);
				System.out.println("수정 결과 : " + result);
				response.sendRedirect("/boardList.board?cpage="+cpage);
			// 게시글 삭제	
			}else if(cmd.equals("/delete.board")) {
				int result = bdao.delete(Integer.parseInt(seq));
				System.out.println("삭제 결과 : " + result);
				response.sendRedirect("/boardList.board?cpage="+cpage);
			}else if(cmd.equals("/search.board")) {
				String select = request.getParameter("select");
				String keyword = request.getParameter("keyword");
				if(keyword==null) {
					response.sendRedirect("boardList.board?cpage="+cpage);
				}else {
					System.out.println("select : " + select);
					System.out.println("keyword : " +keyword);
					int currentPage = Integer.parseInt(cpage);
					int start = currentPage * BoardStatics.RECORD_COUNT_PER_PAGE-9;
					int end = currentPage * BoardStatics.RECORD_COUNT_PER_PAGE;
					List<BoardDTO> boardList = bdao.selectByBoundSearch(start,end,select,keyword);
					String navi = bdao.getPageNaviSearch(currentPage,select,keyword);
					String noSearch = "검색 결과가 없습니다.";
					if(boardList.size()==0) {
						request.setAttribute("noSearch", noSearch);
					}
					request.setAttribute("boardList", boardList);
					request.setAttribute("navi", navi);
					request.getRequestDispatcher("/resources/board/boardSearch.jsp?cpage="+cpage).forward(request, response);
				}
				// 댓글 등록
			}else if(cmd.equals("/doneCmt.board")){
				System.out.println(seq);
				int parent = Integer.parseInt(seq);
				String writer = (String)session.getAttribute("loginID");
				String contents = request.getParameter("contents-cmt");
				System.out.println(writer);
				System.out.println(parent);
				System.out.println(contents);
				int result = cdao.insert(new CommentDTO(0,0,writer,null,parent,contents));
				System.out.println("댓글 insert 결과 : " + result);
				response.sendRedirect("/detail.board?cpage="+cpage+"&seq="+seq);
				// 댓글 삭제
			}else if(cmd.equals("modifyCmt.board")) {
				
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
