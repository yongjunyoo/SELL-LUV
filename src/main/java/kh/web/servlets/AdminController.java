package kh.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);

		try {
			if(cmd.equals("/adminMain.admin")) { // 관리자 메인
				response.sendRedirect("/resources/admin/adminMain.jsp");

			}else if(cmd.equals("/adminCard.admin")) { // 관리자 카드 관리
				response.sendRedirect("/resources/admin/adminCard.jsp");

			}else if(cmd.equals("/adminBoard.admin")) { // 관리자 자유게시판 관리
				response.sendRedirect("/resources/admin/adminBoard.jsp");

			}else if(cmd.equals("/adminMember.admin")) { // 관리자 회원 관리
				response.sendRedirect("/resources/admin/adminMember.jsp");

			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
