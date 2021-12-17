package kh.web.servlets;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.CompanyDAO;
import kh.web.dao.InfluencerDAO;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		CompanyDAO companyDAO = new CompanyDAO();
		InfluencerDAO influencerDAO = new InfluencerDAO();
		
		try {
			// 커뮤니티 프로필 보이기 설정
			if(cmd.equals("/profile.file")) {
				String name = request.getParameter("writer");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				String sysName = companyDAO.findProfile(name);
				if(sysName.equals("")) {
					sysName = influencerDAO.findProfile(name);
				}
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
			// 마이페이지 프로필 설정
			}else if(cmd.equals("/myProfile.file")) {
				String name = request.getParameter("name");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				String sysName = companyDAO.findProfile(name);
				if(sysName.equals("")) {
					sysName = influencerDAO.findProfile(name);
				}
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
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
