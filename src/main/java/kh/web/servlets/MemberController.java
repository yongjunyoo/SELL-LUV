package kh.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		try {
			if(cmd.equals("/login.mem")) {
				
				response.sendRedirect("/resources/login/login.jsp");
				
			}else if(cmd.equals("/loginCheck.mem")) {
				
				response.sendRedirect("/index.jsp");
				
			}else if(cmd.equals("/signup.mem")) {
				
				response.sendRedirect("/resources/signup/selectSignup.jsp");
				
			}else if(cmd.equals("/CPSignup.mem")) {
				
				response.sendRedirect("/resources/signup/CPSignup.jsp");
				
			}else if(cmd.equals("/IFSignup.mem")) {
				
				response.sendRedirect("/resources/signup/IFSignup.jsp");
				
			}else if(cmd.equals("/CPSubmit.mem")) {
				

				
			}else if(cmd.equals("/IFSubmit.mem")) {
				

				
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
