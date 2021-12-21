package kh.web.servlets;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		try {
			// 커뮤니티 프로필 보이기 설정
			if(cmd.equals("/profile.file")) {
				String id = request.getParameter("writer");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				String sysName = companyDAO.findProfile(id);
				if(sysName.equals("")) {
					sysName = influencerDAO.findProfile(id);
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
				String id = (String)session.getAttribute("loginID");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				String sysName = companyDAO.findProfile(id);
				if(sysName.equals("")) {
					sysName = influencerDAO.findProfile(id);
				}
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
			}else if(cmd.equals("/product.file")) { // 기업 리스트에서 제품등록한 사진 불러오기.
				String seq = request.getParameter("seq");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				//String sysName = "user2.png";
				String sysName = companyDAO.productFindbySeq(seq); // 제품등록된 사진의 부모시퀀스를 통해 sysname 추출.
				System.out.print(sysName);
				
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
				
			}else if(cmd.equals("/productDetail.file")) { // 인플루언서 디테일에서 제품등록한 사진 불러오기.
				String seq = request.getParameter("seq");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				//String sysName = "user2.png";
				String pSeq = companyDAO.ifFindbySeq(seq); // seq를 통해 부모seq추출.
				String sysName = companyDAO.productFindbySeq(pSeq); // 제품등록된 사진의 부모시퀀스를 통해 sysname 추출.
				System.out.print(sysName);
				
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
				
			}else if(cmd.equals("/influencerList.file")) { // 인플루언서 리스트에서 프로필 사진 불러오기.
				String seq = request.getParameter("seq");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				//String sysName = "user2.png";
				String pSeq = influencerDAO.ifFindbySeq(seq); // seq를 통해 부모seq추출.
				String sysName = influencerDAO.ifFindbyPseq(pSeq); // 부모seq를 통해 sysname 추출.
				System.out.print(sysName);
				
				String imgpath = path + "/" + sysName;
				try(FileInputStream f = new FileInputStream(imgpath); ){
					int length;
					byte[] buffer = new byte[10];
					while ( ( length = f.read( buffer ) ) != -1 )
						bout.write( buffer, 0, length );  
				}
			}else if(cmd.equals("/influencerDetail.file")) { // 기업 디테일에서 프로필 사진 불러오기.
				String seq = request.getParameter("seq");
				response.setContentType( "image/gif" );
				ServletOutputStream bout = response.getOutputStream();	
				String path = request.getServletContext().getRealPath("files");
				//String sysName = "user2.png";
				String sysName = influencerDAO.ifFindbyPseq(seq); // 부모seq를 통해 sysname 추출.
				System.out.print(sysName);
				
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
			response.sendRedirect("/error.jsp");
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
