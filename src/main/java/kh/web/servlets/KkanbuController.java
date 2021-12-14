package kh.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.CompanyDAO;
import kh.web.dao.CompanyKkanbuRequestDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.dao.InfluencerKkanbuRequestDAO;
import kh.web.dto.CompanyKkanbuRequestDTO;
import kh.web.dto.InfluencerKkanbuRequestDTO;


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
		
	try {
		if(cmd.equals("/kkanbuRequestToInfluencer.kkanbu")) {
			
			int kkanbuSeqFrom = Integer.parseInt(request.getParameter("kkanbuSeqFrom"));
			int kkanbuSeqTo = Integer.parseInt(request.getParameter("kkanbuSeqTo"));
			int profileSeq =  Integer.parseInt(request.getParameter("profileSeq"));
			
			String kkanbuNameFrom = companyDAO.getName(kkanbuSeqFrom);
			String kkanbuNickNameTo = influencerDAO.getNickname(kkanbuSeqTo);
			
//			컨트롤러에서 파라미터 값을 받은후 해당페이지의 member_seq의 마이페이지에 {로그인된 아이디의 시퀀스를 가진 아이디의 아이디값}님으로 부터 깐부요청이 왔습니다 <수락><거절> 을 뛰움 ->
			System.out.println("kkanbuNickNameTo:"+  kkanbuNickNameTo+" "+ " kkanbuNickNameFrom:"+kkanbuNameFrom );
			
			 int requesting = influencerKkanbuRequestDAO.sendKkanbuRequest(kkanbuSeqFrom,kkanbuSeqTo,kkanbuNameFrom,kkanbuNickNameTo);
			 
			
			
			
		}else if(cmd.equals("/kkanbuRequest.kkanbu")) {
			
			
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
