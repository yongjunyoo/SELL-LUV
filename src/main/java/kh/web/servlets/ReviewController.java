package kh.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
	
		
		try {
			if(cmd.equals("/write.reivew")) {
				
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				String raiting= request.getParameter("raiting");
				
				
				
				
				response.sendRedirect("/resources/searchDetail/searchDetail.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}