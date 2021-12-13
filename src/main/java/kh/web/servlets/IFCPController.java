package kh.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kh.web.dao.CompanyDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.dto.Board_CpDTO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.InfluencerDTO;
import kh.web.dto.Profile_IfDTO;
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
				
				LinkedHashMap<Profile_IfDTO,InfluencerDTO> list = influencerDAO.selectByBound(start, end);
				
				for (Entry<Profile_IfDTO, InfluencerDTO> entrySet : list.entrySet()) {
					System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
				}
				
				String navi = companyDAO.getPageNavi(currentPage);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/resources/ifcp/influencerList.jsp").forward(request, response);
				
				//인플루언서 목록 출력 끝..
				//====================================================================================================================================
				//기업기본 목록 출력...
			}else if(cmd.equals("/companyList.ifcp")) {
				int currentPage = Integer.parseInt(request.getParameter("cpage"));
				
				if(currentPage < 1) {currentPage = 1;}

				
				int start = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE- (IFCPStatics.RECORD_COUNT_PER_PAGE-1);;
				int end = currentPage * IFCPStatics.RECORD_COUNT_PER_PAGE;
				
				LinkedHashMap<Board_CpDTO,CompanyDTO> list = companyDAO.selectByBound(start,end);
			
				String navi = companyDAO.getPageNavi(currentPage);
				
				for (java.util.Map.Entry<Board_CpDTO, CompanyDTO> entrySet : list.entrySet()) {
					System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
				}
				
				String cp = "cp";
				
				request.setAttribute("cp", cp);
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.getRequestDispatcher("/resources/ifcp/companyList.jsp").forward(request, response);
				//====================================================================================================================================
				//기업 목록 출력 끝..
				
				
				//====================================================================================================================================
				// 상세페이지 이동.
			}else if(cmd.equals("/companyBoard.ifcp")) {
				
				int seq = Integer.parseInt(request.getParameter("seq"));
				
				System.out.println(seq);
				
				LinkedHashMap<Board_CpDTO,CompanyDTO> list = companyDAO.getCompanyBoardDetail(seq);
				
				
				for (java.util.Map.Entry<Board_CpDTO, CompanyDTO> entrySet : list.entrySet()) {
					System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
				}
				
				request.setAttribute("cpList", list);
				request.getRequestDispatcher("/resources/ifcp/companyDetail.jsp").forward(request, response);

			}else if(cmd.equals("/influencerProfile.ifcp")) {
				
					int seq = Integer.parseInt(request.getParameter("seq"));
				
					LinkedHashMap<Profile_IfDTO,InfluencerDTO> list = influencerDAO.getIfProfile(seq);
					
					for (Entry<Profile_IfDTO, InfluencerDTO> entrySet : list.entrySet()) {
						System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
					}

					request.setAttribute("ifList", list);
					request.getRequestDispatcher("/resources/ifcp/ifProfileDetail.jsp").forward(request, response);
					
					
			
			}else if(cmd.equals("/write.ifcp")) {
				response.sendRedirect("resources/ifcp/writeCompanyDetail.jsp");
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