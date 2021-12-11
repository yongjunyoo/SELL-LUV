package kh.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.CompanyDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.InfluencerDTO;
import kh.web.statics.IFCPStatics;
import kh.web.statics.Table;


@WebServlet("*.ifcp")
public class IFCPController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		InfluencerDAO influencerDAO = new InfluencerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		
		try {
			//====================================================================================================================================
			//인플루언서 기본 목록 출력..
			if(cmd.equals("/influencerList.ifcp")) {
				
				int currentPage = Integer.parseInt(request.getParameter("cpage"));
			
				if(currentPage < 1) {currentPage = 1;}
				
				int start = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE- (IFCPStatics.RECORD_COUNT_PER_PAGE-1);;
				int end = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE;
				
				ArrayList<InfluencerDTO> list = influencerDAO.selectByBound(start,end);
				
				String navi = companyDAO.getPageNavi(currentPage);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/resources/ifcp/list.jsp").forward(request, response);
				
				//인플루언서 목록 출력 끝..
				//====================================================================================================================================
				//기업기본 목록 출력...
			}else if(cmd.equals("/companyList.ifcp")) {
				int currentPage = Integer.parseInt(request.getParameter("cpage"));
				
				if(currentPage < 1) {currentPage = 1;}

				
				int start = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE- (IFCPStatics.RECORD_COUNT_PER_PAGE-1);;
				int end = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE;
				
				ArrayList<CompanyDTO> list = companyDAO.selectByBound(start,end);
			
				String navi = companyDAO.getPageNavi(currentPage);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/resources/ifcp/list.jsp").forward(request, response);
				//====================================================================================================================================
				//기업 목록 출력 끝..
				
				//====================================================================================================================================
				//등급별 조회..
			}else if(cmd.equals("/listSortByGrade.ifcp")){
				
				String object = request.getParameter("object");
				System.out.println(object);
				
				if(object.contains("Influencer")) {
					System.out.println("influencer");
					response.sendRedirect("/influencerList.ifcp?cpage=1");
				}else if(object.contains("Company")) {
					System.out.println("company");
					response.sendRedirect("/companyList.ifcp?cpage=1");
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
