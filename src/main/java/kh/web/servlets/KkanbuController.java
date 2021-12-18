package kh.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kh.web.dao.CompanyDAO;
import kh.web.dao.CompanyKkanbuRequestDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.dao.InfluencerKkanbuRequestDAO;
import kh.web.dao.KkanbuDAO;
import kh.web.dto.CompanyKkanbuRequestDTO;
import kh.web.dto.InfluencerKkanbuRequestDTO;
import kh.web.dto.KkanbuDTO;


@WebServlet("*.kkanbu")
public class KkanbuController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		InfluencerDAO influencerDAO = new InfluencerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		InfluencerKkanbuRequestDAO influencerKkanbuRequestDAO = new InfluencerKkanbuRequestDAO();
		CompanyKkanbuRequestDAO companyKkanbuRequestDAO = new CompanyKkanbuRequestDAO();
		InfluencerKkanbuRequestDTO 	influencerKkanbuRequestDTO = new InfluencerKkanbuRequestDTO();
		CompanyKkanbuRequestDTO companyKkanbuRequestDTO = new CompanyKkanbuRequestDTO();
		KkanbuDAO kkanbuDAO = new KkanbuDAO();
		KkanbuDTO kkanbuDTO = new KkanbuDTO();
		
	try {
		if(cmd.equals("/kkanbuRequestToInfluencer.kkanbu")) {
			
			int kkanbuSeqFrom = Integer.parseInt(request.getParameter("kkanbuSeqFrom"));
			int kkanbuSeqTo = Integer.parseInt(request.getParameter("kkanbuSeqTo"));
			int kkanbuCardSeq = Integer.parseInt(request.getParameter("kkanbuCardSeq"));
			int cpage =Integer.parseInt(request.getParameter("cpage"));
			
			 System.out.println("kkanbuCardSeq: " + kkanbuCardSeq );

			
			String kkanbuNameFrom = companyDAO.getName(kkanbuSeqFrom);
			String kkanbuNickNameTo = influencerDAO.getNickname(kkanbuSeqTo);
			
			
//			파라미터 값을 받은후 해당페이지의 member_seq의 마이페이지에 {로그인된 아이디의 시퀀스를 가진 아이디의 아이디값}으로 부터 깐부요청 <수락><거절> 을 뛰움 ->
			System.out.println("kkanbuNickNameTo:"+  kkanbuNickNameTo+" "+ " kkanbuNickNameFrom:"+kkanbuNameFrom );
			
			 boolean isRequestStillPending = influencerKkanbuRequestDAO.isRequestStillPending(kkanbuSeqFrom,kkanbuSeqTo);
			 boolean areTheyKkanbu = kkanbuDAO.areTheyKkanbu(kkanbuSeqFrom,kkanbuSeqTo);
			 
			 System.out.println(isRequestStillPending);
			 	
			 	if(areTheyKkanbu) {
					
//			 		int kkanbuSeq = kkanbuDAO.getKkanbuSeq(kkanbuSeqFrom,kkanbuSeqTo);
			 		
			 		String kkanbuMessage= "<div class=\"nav tag-cloud\"><span>우린 깐부잖아..</span></div>	";
			 		
//			 		request.setAttribute("kkanbuSeq",kkanbuSeq );
			 		request.setAttribute("kkanbuMessage",kkanbuMessage);
					RequestDispatcher rd =request.getRequestDispatcher("/influencerProfile.ifcp?seq="+kkanbuCardSeq+"&cpage="+cpage);  
					rd.forward(request, response);
			 	
			 
			 	}else if(!isRequestStillPending) {
					
					int requesting = influencerKkanbuRequestDAO.sendKkanbuRequest(kkanbuSeqFrom,kkanbuSeqTo,kkanbuNameFrom,kkanbuNickNameTo);
					
					response.sendRedirect("/influencerProfile.ifcp?seq="+kkanbuCardSeq+"&cpage="+cpage);
					
					
				}else if(isRequestStillPending) {
					
					String errorMessage = "이미 깐부요청을 하셨습니다..";

					request.setAttribute("errorMessage", errorMessage);
					request.setAttribute("kkanbuCardSeq", kkanbuCardSeq);
					RequestDispatcher rd =request.getRequestDispatcher("/influencerProfile.ifcp?seq="+kkanbuCardSeq+"&cpage="+cpage);  
					rd.forward(request, response);
				}
			
			
		}else if(cmd.equals("/kkanbuRequestToCompany.kkanbu")) {
			
			int kkanbuSeqFrom = Integer.parseInt(request.getParameter("kkanbuSeqFrom"));
			int kkanbuSeqTo = Integer.parseInt(request.getParameter("kkanbuSeqTo"));
			int kkanbuCardSeq = Integer.parseInt(request.getParameter("kkanbuCardSeq"));
			int cpage =Integer.parseInt(request.getParameter("cpage"));
			
			 System.out.println("kkanbuCardSeq: " + kkanbuCardSeq );
			 System.out.println("kkanbuSeqFrom: " + kkanbuSeqFrom + "kkanbuSeqTo "+ kkanbuSeqTo);


			
			String kkanbuNameTo = companyDAO.getName(kkanbuSeqTo);
			String kkanbuNickNameFrom = influencerDAO.getNickname(kkanbuSeqFrom);
			
			
//			파라미터 값을 받은후 해당페이지의 member_seq의 마이페이지에 {로그인된 아이디의 시퀀스를 가진 아이디의 아이디값}으로 부터 깐부요청 <수락><거절> 을 뛰움 ->
			System.out.println("kkanbuNickNameTo:"+  kkanbuNickNameFrom+" "+ " kkanbuNameFrom:"+kkanbuNameTo );
			
			 boolean isRequestStillPending = companyKkanbuRequestDAO.isRequestStillPending(kkanbuSeqFrom,kkanbuSeqTo);
			 boolean areTheyKkanbu = kkanbuDAO.areTheyKkanbu(kkanbuSeqFrom,kkanbuSeqTo);
			
			 System.out.println(isRequestStillPending);
			 
			 if(areTheyKkanbu) {
					
//			 		int kkanbuSeq = kkanbuDAO.getKkanbuSeq(kkanbuSeqFrom,kkanbuSeqTo);
			 		
			 		String kkanbuMessage= "<div class=\"nav tag-cloud\"><span>우린 깐부잖아..</span></div>	";
			 		
//			 		request.setAttribute("kkanbuSeq",kkanbuSeq );
			 		request.setAttribute("kkanbuMessage",kkanbuMessage);
					RequestDispatcher rd =request.getRequestDispatcher("/influencerProfile.ifcp?seq="+kkanbuCardSeq+"&cpage="+cpage);  
					rd.forward(request, response);
			 
			 }else if(!isRequestStillPending) {
					
					int requesting = companyKkanbuRequestDAO.sendKkanbuRequest(kkanbuSeqFrom,kkanbuSeqTo,kkanbuNickNameFrom,kkanbuNameTo);
					
					response.sendRedirect("/companyDetail.ifcp?kkanbuCardSeq="+kkanbuCardSeq+"&cpage="+cpage);
					
					
				}else if(isRequestStillPending) {
					String errorMessage = "이미 깐부요청을 하셨습니다..";

					request.setAttribute("errorMessage", errorMessage);
					request.setAttribute("kkanbuCardSeq", kkanbuCardSeq);
					RequestDispatcher rd =request.getRequestDispatcher("/influencerProfile.ifcp?seq="+kkanbuCardSeq+"&cpage="+cpage);  
					rd.forward(request, response);
				}
			
		}else if(cmd.equals("/showKkanbuRequest.kkanbu")) {
			
			int loggedInSeq = Integer.parseInt(request.getParameter("IDseq"));
			
			System.out.println(loggedInSeq);
			
			ArrayList<KkanbuDTO> kkanbuList = kkanbuDAO.getInfKkanbu(loggedInSeq);
			
			List<InfluencerKkanbuRequestDTO> kkanbuRequest = influencerKkanbuRequestDAO.getKkanbuRequest(loggedInSeq);
			
			for( InfluencerKkanbuRequestDTO kkanbu : kkanbuRequest) {
				System.out.println(kkanbu.getIf_kkanbu_seq()+ " " + kkanbu.getIf_kkanbuNameFrom());
			}
			
			request.setAttribute("kkanbuRequest", kkanbuRequest);
			request.setAttribute("kkanbuList", kkanbuList);
			request.getRequestDispatcher("/resources/mypage/IFmypageKkanbu.jsp").forward(request, response);
			
			
			
		}else if(cmd.equals("/showCompanyKkanbuRequest.kkanbu")) {
			int loggedInSeq = Integer.parseInt(request.getParameter("IDseq"));
			
			System.out.println(loggedInSeq);
			
			List<CompanyKkanbuRequestDTO> kkanbuRequest = companyKkanbuRequestDAO.getKkanbuRequest(loggedInSeq);
			
			for( CompanyKkanbuRequestDTO kkanbu : kkanbuRequest) {
				System.out.println(kkanbu.getCp_kkanbu_seq()+ " " + kkanbu.getCp_kkanbuNameFrom());
			}
			
			request.setAttribute("kkanbuRequest", kkanbuRequest);
			request.getRequestDispatcher("/resources/mypage/CPmypageKkanbu.jsp").forward(request, response);
			
			
			
		}else if(cmd.equals("/deleteInfKkanbuRequest.kkanbu")) {
			
			int kkanbuSeq = Integer.parseInt(request.getParameter("kkanbuSeq"));
			int kkanbuTo = Integer.parseInt(request.getParameter("kkanbuTo"));
			
			int result = influencerKkanbuRequestDAO.delete(kkanbuSeq); 
			
			response.sendRedirect("/showKkanbuRequest.kkanbu?IDseq="+kkanbuTo);
			
		}else if(cmd.equals("/deleteCompanyKkanbuRequest")) {
			int kkanbuSeq = Integer.parseInt(request.getParameter("kkanbuSeq"));
			int kkanbuTo = Integer.parseInt(request.getParameter("kkanbuTo"));
		
			int result = companyKkanbuRequestDAO.delete(kkanbuSeq); 
			
			response.sendRedirect("/showKkanbuRequest.kkanbu?IDseq="+kkanbuTo);
			
			
		}else if(cmd.equals("/approveInfKkanbuRequest.kkanbu")) {

			int companySeq = Integer.parseInt(request.getParameter("kkanbuFrom"));
			int influencerSeq = Integer.parseInt(request.getParameter("kkanbuTo"));
			int kkanbu_seq = Integer.parseInt(request.getParameter("kkanbuSeq"));
			
			System.out.println(companySeq+" " + influencerSeq);
			
			int result = kkanbuDAO.insertKkanbu(companySeq,influencerSeq);
			response.sendRedirect("/deleteInfKkanbuRequest.kkanbu?kkanbuSeq="+kkanbu_seq+"&kkanbuTo="+companySeq);
			
//			response.sendRedirect("/showKkanbuRequest.kkanbu?IDseq="+influencerSeq);
			
		}else if(cmd.equals("/approveCompanyKkanbuRequest.kkanbu")) {
			int companySeq = Integer.parseInt(request.getParameter("kkanbuTo"));
			int influencerSeq = Integer.parseInt(request.getParameter("kkanbuFrom"));
			int kkanbu_seq = Integer.parseInt(request.getParameter("kkanbu_seq"));
			
			int result = kkanbuDAO.insertKkanbu(influencerSeq,companySeq);
			
//			response.sendRedirect("/showCompanyKkanbuRequest.kkanbu?IDseq="+companySeq);
			response.sendRedirect("/deleteCompanyKkanbuRequest.kkanbu?kkanbuSeq="+kkanbu_seq+"&kkanbuTo="+companySeq);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
