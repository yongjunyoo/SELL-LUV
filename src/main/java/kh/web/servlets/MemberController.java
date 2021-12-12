package kh.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.web.dao.CompanyDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.web.SHA512;


@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지

		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);		

		InfluencerDAO influencerDAO = new InfluencerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		
		SHA512 sha512 = new SHA512();
		
		try {
			if(cmd.equals("/influencerLogin.mem")) { //인플루언서 로그인 부분...
				
				String id = request.getParameter("id_if");
				String pw = sha512.generate(request.getParameter("pw_if"));
				
				boolean result = influencerDAO.login(id, pw);
				System.out.println(id+pw);
				
				if(result) {
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
					
					System.out.println("logged in!");
		
					response.sendRedirect("/index.jsp");
					
				}else if(!result) {
					System.out.println( id+ "로그인실패..");
					String idResult = String.valueOf(result);
					request.setAttribute("result", idResult);
					RequestDispatcher rd =request.getRequestDispatcher("resources/login/login.jsp");  
					rd.forward(request, response);

				}





			}else if(cmd.equals("/companyLogin.mem")) { //기업 로그인 부분...

				String id = request.getParameter("id_cp");
				String pw = sha512.generate(request.getParameter("pw_cp"));

				boolean result = companyDAO.login(id, pw);

				System.out.println(id + " " + pw + result);
				String idResult = String.valueOf(result);

				if(result) {
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
					System.out.println( id+ "logged in!");
					response.sendRedirect("/index.jsp");

				}else if(!result) {
					System.out.println( id+ "로그인실패..");
					String errorMessage = "정확한 정보를 입력하세요..";

					request.setAttribute("errorMessage", errorMessage);
					RequestDispatcher rd =request.getRequestDispatcher("/resources/login/login.jsp");  
					rd.forward(request, response);

				}


			}else if(cmd.equals("/logout.mem")) {
				request.getSession().removeAttribute("loginID");
				response.sendRedirect("/index.jsp");
			}else if(cmd.equals("/loginCheck.mem")) {

				response.sendRedirect("/index.jsp");

			}else if(cmd.equals("/CPSubmit.mem")) {

				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String photo = request.getParameter("photo");
				String name = request.getParameter("name");
				String crunumber = request.getParameter("crunumber");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String rpt_cp = request.getParameter("rpt_cp");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String sales = request.getParameter("sales");
				String grade = request.getParameter("grade");
				String pwAsk = request.getParameter("pwAsk");
				String pwAnswer = request.getParameter("pwAnswer");

				int result = companyDAO.insert(id, sha512.generate(pw), photo, name, crunumber, zipcode, address1, address2, rpt_cp, phone, email, sales, grade, pwAsk, pwAnswer);

				response.sendRedirect("/resources/login/login.jsp");
				
			}else if(cmd.equals("/CPidCheck.mem")) {

				String id = request.getParameter("id");
				boolean result = companyDAO.isIdExist(id);
				response.getWriter().append(String.valueOf(result));
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
