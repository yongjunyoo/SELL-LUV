package kh.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.AdminDAO;
import kh.web.dto.BoardDTO;
import kh.web.dto.Board_CpDTO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.InfluencerDTO;
import kh.web.dto.Profile_IfDTO;
import kh.web.statics.PageStatics;


@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);

		AdminDAO dao = AdminDAO.getInstance();

		try {
			if(cmd.equals("/adminMain.admin")) { // 관리자 메인
				int cpCard = dao.getCpCardCount();
				int ifCard = dao.getIfCardCount();
				int cardResult = cpCard+ifCard;
				int boardResult = dao.getAllBoardCount();
				int memberResult = dao.getAllMemberCount();
				int[] gradeResult = dao.getGradeCount();

				request.setAttribute("card", cardResult);
				request.setAttribute("board", boardResult);
				request.setAttribute("member", memberResult);
				request.setAttribute("grade", gradeResult);

				request.getRequestDispatcher("/resources/admin/adminMain.jsp").forward(request, response);

			}else if(cmd.equals("/adminIfCard.admin")) { // 관리자 인플루언서 카드 관리
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getifCardPageTotalCount()) {
					currentPage = dao.getifCardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<Profile_IfDTO> list = dao.ifCardBoundary(start, end);

				String navi = dao.getifCardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int ifCard = dao.getIfCardCount();
				request.setAttribute("ifCard", ifCard);
				request.getRequestDispatcher("/resources/admin/adminIfCard.jsp").forward(request, response);

			}else if(cmd.equals("/adminCpCard.admin")) { // 관리자 기업 카드 관리
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getCpCardPageTotalCount()) {
					currentPage = dao.getCpCardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<Board_CpDTO> list = dao.cpCardBoundary(start, end);

				String navi = dao.getCpCardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int cpCard = dao.getCpCardCount();
				request.setAttribute("cpCard", cpCard);
				request.getRequestDispatcher("/resources/admin/adminCpCard.jsp").forward(request, response);

			}else if(cmd.equals("/adminBoard.admin")) { // 관리자 자유게시판 관리
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getBoardPageTotalCount()) {
					currentPage = dao.getBoardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<BoardDTO> list = dao.boardBoundary(start, end);

				String navi = dao.getBoardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int board = dao.getAllBoardCount();
				request.setAttribute("board", board);
				request.getRequestDispatcher("/resources/admin/adminBoard.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminCpMember.admin")) { // 관리자 기업 회원 관리
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getCpMemberPageTotalCount()) {
					currentPage = dao.getCpMemberPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<CompanyDTO> list = dao.cpMemberBoundary(start, end);

				String navi = dao.getCpMemberPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int member = dao.getCpMemberCount();
				request.setAttribute("member", member);
				request.getRequestDispatcher("/resources/admin/adminCpMember.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminIfMember.admin")) { // 관리자 인플루언서 회원 관리
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getIfMemberPageTotalCount()) {
					currentPage = dao.getIfMemberPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<InfluencerDTO> list = dao.ifMemberBoundary(start, end);

				String navi = dao.getIfMemberPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int member = dao.getIfMemberCount();
				request.setAttribute("member", member);
				request.getRequestDispatcher("/resources/admin/adminIfMember.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminBoardSearch.admin")) { // 자유게시판 글 찾기
				String searchContents = request.getParameter("searchContents");
				String select = request.getParameter("select");
				
				if(select.equals("제목")) { //제목으로 찾기.					
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getBoardPageTotalCount()) {
						currentPage = dao.getBoardPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<BoardDTO> list = dao.boardSearchByTitle(searchContents, start, end);

					String navi = dao.getBoardPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int board = dao.getAllBoardCount();
					request.setAttribute("board", board);
					request.getRequestDispatcher("/resources/admin/adminBoard.jsp").forward(request, response);
					
				}else { // 작성자로 찾기.
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getBoardPageTotalCount()) {
						currentPage = dao.getBoardPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<BoardDTO> list = dao.boardSearchByWriter(searchContents, start, end);

					String navi = dao.getBoardPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int board = dao.getAllBoardCount();
					request.setAttribute("board", board);
					request.getRequestDispatcher("/resources/admin/adminBoard.jsp").forward(request, response);
				}
				
			}else if(cmd.equals("/adminCpCardSearch.admin")) { // 기업 카드 글 찾기
				String searchContents = request.getParameter("searchContents");
				String select = request.getParameter("select");
				
				if(select.equals("제목")) { //제목으로 찾기.					
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getCpCardPageTotalCount()) {
						currentPage = dao.getCpCardPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<Board_CpDTO> list = dao.cpCardSearchByTitle(searchContents,start, end);

					String navi = dao.getCpCardPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int cpCard = dao.getCpCardCount();
					request.setAttribute("cpCard", cpCard);
					request.getRequestDispatcher("/resources/admin/adminCpCard.jsp").forward(request, response);

				}else { // 작성자로 찾기.
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getCpCardPageTotalCount()) {
						currentPage = dao.getCpCardPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<Board_CpDTO> list = dao.cpCardSearchByWriter(searchContents,start, end);

					String navi = dao.getCpCardPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int cpCard = dao.getCpCardCount();
					request.setAttribute("cpCard", cpCard);
					request.getRequestDispatcher("/resources/admin/adminCpCard.jsp").forward(request, response);
				}
				
			}else if(cmd.equals("/adminCpMemberSearch.admin")) { // 기업 회원 글 찾기
				String searchContents = request.getParameter("searchContents");
				String select = request.getParameter("select");
				
				if(select.equals("아이디")) { //아이디로 찾기.					
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getCpMemberPageTotalCount()) {
						currentPage = dao.getCpMemberPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<CompanyDTO> list = dao.cpMemberSearchById(searchContents,start, end);

					String navi = dao.getCpMemberPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int member = dao.getCpMemberCount();
					request.setAttribute("member", member);
					request.getRequestDispatcher("/resources/admin/adminCpMember.jsp").forward(request, response);
				}else { // 기업이름으로 찾기.
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getCpMemberPageTotalCount()) {
						currentPage = dao.getCpMemberPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<CompanyDTO> list = dao.cpMemberSearchByName(searchContents,start, end);

					String navi = dao.getCpMemberPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int member = dao.getCpMemberCount();
					request.setAttribute("member", member);
					request.getRequestDispatcher("/resources/admin/adminCpMember.jsp").forward(request, response);
				}
				
			}else if(cmd.equals("/adminIfCardSearch.admin")) { // 인플루언서 카드 글 찾기
				String searchContents = request.getParameter("searchContents");
				String select = request.getParameter("select");
				
				if(select.equals("작성자")) { // 작성자로 찾기.					
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getifCardPageTotalCount()) {
						currentPage = dao.getifCardPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<Profile_IfDTO> list = dao.ifCardSearchByWriter(searchContents,start, end);

					String navi = dao.getifCardPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int ifCard = dao.getIfCardCount();
					request.setAttribute("ifCard", ifCard);
					request.getRequestDispatcher("/resources/admin/adminIfCard.jsp").forward(request, response);
				}
				
			}else if(cmd.equals("/adminIfMemberSearch.admin")) { // 인플루언서 회원 글 찾기
				String searchContents = request.getParameter("searchContents");
				String select = request.getParameter("select");
				
				if(select.equals("아이디")) { //아이디로 찾기.					
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getIfMemberPageTotalCount()) {
						currentPage = dao.getIfMemberPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<InfluencerDTO> list = dao.ifMemberSearchById(searchContents,start, end);

					String navi = dao.getIfMemberPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int member = dao.getIfMemberCount();
					request.setAttribute("member", member);
					request.getRequestDispatcher("/resources/admin/adminIfMember.jsp").forward(request, response);
				}else if(select.equals("이름")) { // 이름으로 찾기.
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getIfMemberPageTotalCount()) {
						currentPage = dao.getIfMemberPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<InfluencerDTO> list = dao.ifMemberSearchByName(searchContents,start, end);

					String navi = dao.getIfMemberPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int member = dao.getIfMemberCount();
					request.setAttribute("member", member);
					request.getRequestDispatcher("/resources/admin/adminIfMember.jsp").forward(request, response);
				}else { // 닉네임으로 찾기.
					int currentPage = Integer.parseInt(request.getParameter("cpage"));

					if(currentPage < 1) { 
						currentPage = 1;
					}else if(currentPage > dao.getIfMemberPageTotalCount()) {
						currentPage = dao.getIfMemberPageTotalCount();
					}

					int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
					int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

					List<InfluencerDTO> list = dao.ifMemberSearchByNickname(searchContents,start, end);

					String navi = dao.getIfMemberPageNavi(currentPage); 
					request.setAttribute("navi", navi);
					request.setAttribute("list", list); 

					int member = dao.getIfMemberCount();
					request.setAttribute("member", member);
					request.getRequestDispatcher("/resources/admin/adminIfMember.jsp").forward(request, response);
				}
			}else if(cmd.equals("/adminBoardDelete.admin")) { // 자유게시판 글 삭제
				String[] checkBox = request.getParameterValues("checkbox");
				
				for(int i=0; i<checkBox.length; i++) {
					int cb = Integer.parseInt(checkBox[i]);
					dao.deleteBoard(cb);
				}
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getBoardPageTotalCount()) {
					currentPage = dao.getBoardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<BoardDTO> list = dao.boardBoundary(start, end);

				String navi = dao.getBoardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int board = dao.getAllBoardCount();
				request.setAttribute("board", board);
				request.getRequestDispatcher("/resources/admin/adminBoard.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminCpCardDelete.admin")) { // 기업 카드 관리 글 삭제
				String[] checkBox = request.getParameterValues("checkbox");
				
				for(int i=0; i<checkBox.length; i++) {
					int cb = Integer.parseInt(checkBox[i]);
					dao.deleteCpCard(cb);
				}
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getCpCardPageTotalCount()) {
					currentPage = dao.getCpCardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<Board_CpDTO> list = dao.cpCardBoundary(start, end);

				String navi = dao.getCpCardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int cpCard = dao.getCpCardCount();
				request.setAttribute("cpCard", cpCard);
				request.getRequestDispatcher("/resources/admin/adminCpCard.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminCpMemberDelete.admin")) { // 기업 회원 관리 글 삭제
				String[] checkBox = request.getParameterValues("checkbox");
				
				for(int i=0; i<checkBox.length; i++) {
					int cb = Integer.parseInt(checkBox[i]);
					dao.deleteCpMember(cb);
				}
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getCpMemberPageTotalCount()) {
					currentPage = dao.getCpMemberPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<CompanyDTO> list = dao.cpMemberBoundary(start, end);

				String navi = dao.getCpMemberPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int member = dao.getCpMemberCount();
				request.setAttribute("member", member);
				request.getRequestDispatcher("/resources/admin/adminCpMember.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminIfCardDelete.admin")) { // 인플루언서 카드 관리 글 삭제
				String[] checkBox = request.getParameterValues("checkbox");
				
				for(int i=0; i<checkBox.length; i++) {
					int cb = Integer.parseInt(checkBox[i]);
					dao.deleteIfCard(cb);
				}
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getifCardPageTotalCount()) {
					currentPage = dao.getifCardPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<Profile_IfDTO> list = dao.ifCardBoundary(start, end);

				String navi = dao.getifCardPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int ifCard = dao.getIfCardCount();
				request.setAttribute("ifCard", ifCard);
				request.getRequestDispatcher("/resources/admin/adminIfCard.jsp").forward(request, response);
				
			}else if(cmd.equals("/adminIfMemberDelete.admin")) { // 인플루언서 회원 관리 글 삭제
				String[] checkBox = request.getParameterValues("checkbox");
				
				for(int i=0; i<checkBox.length; i++) {
					int cb = Integer.parseInt(checkBox[i]);
					dao.deleteIfMember(cb);
				}
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));

				if(currentPage < 1) { 
					currentPage = 1;
				}else if(currentPage > dao.getIfMemberPageTotalCount()) {
					currentPage = dao.getIfMemberPageTotalCount();
				}

				int start = currentPage * PageStatics.RECORD_COUNT_PER_PAGE - (PageStatics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage * PageStatics.RECORD_COUNT_PER_PAGE;

				List<InfluencerDTO> list = dao.ifMemberBoundary(start, end);

				String navi = dao.getIfMemberPageNavi(currentPage); 
				request.setAttribute("navi", navi);
				request.setAttribute("list", list); 

				int member = dao.getIfMemberCount();
				request.setAttribute("member", member);
				request.getRequestDispatcher("/resources/admin/adminIfMember.jsp").forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
