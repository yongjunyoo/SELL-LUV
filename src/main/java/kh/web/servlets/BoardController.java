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
import kh.web.dao.CommentDAO;
import kh.web.dao.CompanyDAO;
import kh.web.dao.InfluencerDAO;
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
		CompanyDAO companyDAO = new CompanyDAO();
		InfluencerDAO influencerDAO = new InfluencerDAO();
		
		try {
		// cpage, seq 세션에 저장
		session.setAttribute("cpage", request.getParameter("cpage"));
		String cpage = (String)session.getAttribute("cpage");
		session.setAttribute("seq", request.getParameter("seq"));
		String seq = (String)session.getAttribute("seq");
		String IDseq = (String)session.getAttribute("IDseq");
		// name 세션에 저장
		String loginName = companyDAO.findName((String)session.getAttribute("loginID"));
		if(loginName=="") {
			loginName = influencerDAO.findName((String)session.getAttribute("loginID"));
		}
		session.setAttribute("loginName", loginName);
		
		System.out.println("cpage : " + request.getParameter("cpage"));
		System.out.println("세션 cpage : " + cpage);
		System.out.println("seq : " + request.getParameter("seq"));
		System.out.println("세션 seq : " + seq);
		
		System.out.println("로그인 ID : " + session.getAttribute("loginID"));
		System.out.println("세션 name : "+session.getAttribute("loginName"));
		if(cpage == null) {cpage="1";}
		
			// 게시판 목록
			if(cmd.equals("/boardList.board")) {
				int currentPage = Integer.parseInt(cpage);
				
				
				int pageTotalCount  = bdao.getPageTotalCount();
				if(currentPage <1) {currentPage=1;}
				if(currentPage > pageTotalCount) {currentPage = pageTotalCount;}
				
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
				String writer = (String)session.getAttribute("loginID");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = bdao.insert(new BoardDTO(0,writer,title,contents,null,0,null));
				System.out.println("게시글 insert 결과 : " + result);
				response.sendRedirect("/boardList.board?cpage="+cpage);
			// 게시글 페이지로 이동
			}else if(cmd.equals("/detail.board")) {
				BoardDTO dto = bdao.selectBySeq(Integer.parseInt(seq));
				// 조회수 올리기
				bdao.addViewCount(Integer.parseInt(seq));
				request.setAttribute("dto", dto);
				// 인플루언서인지 기업인지 (게시글)
				boolean isCompanyMem = companyDAO.isMember(dto.getWriter());
				if (isCompanyMem) {
					request.setAttribute("member", "기업");
				}else {
					request.setAttribute("member", "인플루언서");
				}
				// 댓글 가져가기
				List<CommentDTO> cList = cdao.selectByBoardSeqAddName(Integer.parseInt(seq));
				request.setAttribute("cList", cList);
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
				
				if(session.getAttribute("loginID").equals("kkanbu")) {
					response.sendRedirect("/adminBoard.admin?cpage=1");
				}else {
					response.sendRedirect("/boardList.board?cpage="+cpage);		
				}
			
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
				int parent = Integer.parseInt(seq);
				String writer = (String)session.getAttribute("loginID");
				String contents = request.getParameter("contents-cmt");
				// 인플루언서인지 기업인지
				boolean isCompanyMem = companyDAO.isMember(writer);
				String member = "";
				if (isCompanyMem) {
					member = "기업";
				}else {
					member = "인플루언서";
				}
				int result = cdao.insert(new CommentDTO(0,0,writer,null,parent,contents,member,null));
				System.out.println("댓글 insert 결과 : " + result);
				response.sendRedirect("/detail.board?cpage="+cpage+"&seq="+seq);
				// 댓글 수정
			}else if(cmd.equals("/modifyCmt.board")) {
				String contents = request.getParameter("contents-cmt");
				int cseq = Integer.parseInt(request.getParameter("cseq"));
				int result = cdao.modify(cseq, contents);
				System.out.println("댓글 modify 결과 : " + result);
				response.sendRedirect("/detail.board?cpage="+cpage+"&seq="+seq);
			}else if(cmd.equals("/delCmt.board")) {
				int cseq = Integer.parseInt(request.getParameter("cseq"));
				int result = cdao.delete(cseq);
				System.out.println("댓글 delete 결과 : " + result);
				response.sendRedirect("/detail.board?cpage="+cpage+"&seq="+seq);
			}	
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
		
		
		
		
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
