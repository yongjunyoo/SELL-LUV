package kh.web.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.web.dao.CompanyDAO;
import kh.web.dao.FileDAO;
import kh.web.dao.InfluencerDAO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.FileDTO;
import kh.web.dto.InfluencerDTO;
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
		FileDAO fileDAO = new FileDAO();

		SHA512 sha512 = new SHA512();

		try {
			if(cmd.equals("/influencerLogin.mem")) { //인플루언서 로그인 부분...

				String id = request.getParameter("id_if");
				String pw = sha512.generate(request.getParameter("pw_if"));

				boolean result = influencerDAO.login(id, pw);

				int result2 = influencerDAO.findSeq(id,pw);
				String seq = String.valueOf(result2);
				String name = influencerDAO.findName(id, pw);
				
				System.out.println(id+pw+seq);
				
				if(result) {
					
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
					session.setAttribute("IDseq", seq);
					session.setAttribute("name", name);
					System.out.println("인플루언서 닉네임 : "+name);
					System.out.println("logged in!");

					response.sendRedirect("/index.jsp");

				}else if(!result) {
					
					String errorMessage = "정확한 정보를 입력하세요..";

					request.setAttribute("errorMessage", errorMessage);
					RequestDispatcher rd =request.getRequestDispatcher("/resources/login/login.jsp");  
					rd.forward(request, response);
				}





			}else if(cmd.equals("/companyLogin.mem")) { //기업 로그인 부분...

				String id = request.getParameter("id_cp");
				String pw = sha512.generate(request.getParameter("pw_cp"));

				boolean result = companyDAO.login(id, pw);

				String idResult = String.valueOf(result);
	
				int result2 = companyDAO.findSeq(id,pw);
				String seq = String.valueOf(result2);
				String name = companyDAO.findName(id, pw);
				
				System.out.println(id+pw+"   "+seq);

				if(result) {
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
					session.setAttribute("IDseq", seq);
					session.setAttribute("name", name);
					System.out.println("기업 상호 : "+name);
					System.out.println( id + " " + seq + "logged in!");
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

				// 로그인 페이지로 이동	
			}else if(cmd.equals("/login.mem")) {
				response.sendRedirect("/resources/login/login.jsp");
			}else if(cmd.equals("/CPSubmit.mem")) {
				
				// 파일 업로드 기능 추가
				
				// 파일 저장할 위치 생성
				String savePath = request.getServletContext().getRealPath("files");
				File filePath = new File(savePath);
				if(!filePath.exists()) {filePath.mkdir();}
				
				// 업로드 된 파일 저장 (COS 사용)
				int maxSize = 1024*1024*10; // 10MB
				MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());
				
				// request를 multi로 업그레이드
				String sysName = multi.getFilesystemName("photo");
				String oriName = multi.getOriginalFileName("photo");
				String id = multi.getParameter("id");
				String pw = multi.getParameter("pw");
				String name = multi.getParameter("name");
				String crunumber = multi.getParameter("crunumber");
				String zipcode = multi.getParameter("zipcode");
				String address1 = multi.getParameter("address1");
				String address2 = multi.getParameter("address2");
				String rpt_cp = multi.getParameter("rpt_cp");
				String phone = multi.getParameter("phone");
				String email = multi.getParameter("email");
				String sales = multi.getParameter("sales");
				String grade = multi.getParameter("grade");
				String pwAsk = multi.getParameter("pwAsk");
				String pwAnswer = multi.getParameter("pwAnswer");
				// company 테이블의 seq 생성
				int cpSeq = companyDAO.createNewseq();
				
				// 홈 디렉토리 경로 : E:\2021_09_웹응용과정\workspace_backend\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SLproject\
				// 회원 정보 insert
				int result = companyDAO.insert(cpSeq, id, sha512.generate(pw), sysName, name, crunumber, zipcode, address1, address2, rpt_cp, phone, email, sales, grade, pwAsk, pwAnswer);
				System.out.println("기업 회원가입 결과 : "+result);
				// 프로필 사진 별도로 insert 
				int fileResult = fileDAO.insertCp(new FileDTO(0,oriName,sysName,cpSeq));
				System.out.println("기업 프로필 사진 insert 결과 : " + fileResult);
				
				response.sendRedirect("/resources/login/login.jsp");

			}else if(cmd.equals("/CPidCheck.mem")) {

				String id = request.getParameter("id");
				boolean result = companyDAO.isIdExist(id);
				response.getWriter().append(String.valueOf(result));
				// 비밀번호 찾기 이동
			}else if(cmd.equals("/findpw.mem")) {
				response.sendRedirect("/resources/login/findpw.jsp");
				// 기업멤버 맞는지 확인
			}else if(cmd.equals("/isCPMember.mem")) {
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String text = request.getParameter("text");
				String answer = request.getParameter("answer");
				boolean result = companyDAO.isMember(id, name, text, answer);
				response.getWriter().append(""+result);
				// 기업 비밀번호 찾기 
			}else if(cmd.equals("/companyFindpw.mem")) {
				String id = request.getParameter("id_cp");
				String name = request.getParameter("name_cp");
				String text = request.getParameter("check-text-cp");
				String answer = request.getParameter("answer-cp");
				CompanyDTO dto = companyDAO.findMember(id, name, text, answer);
				System.out.println("회원 정보 검색 결과 : "+ dto.getId());
				request.setAttribute("cpdto", dto);
				request.getRequestDispatcher("/resources/login/findcppwDetail.jsp").forward(request, response);
				// 기업 비밀번호 재설정 	
			}else if(cmd.equals("/resetCPpw.mem")) {
				String pw = sha512.generate(request.getParameter("pw_cp"));
				String id = request.getParameter("id");
				System.out.println(id + "의 재설정한 비밀번호 : "+pw);
				int result = companyDAO.updateNewPW(id, pw);
				System.out.println("비밀번호 재설정 결과 : "+result);
				response.sendRedirect("/resources/login/login.jsp");
				// 인플루언서멤버 맞는지 확인
			}else if(cmd.equals("/isIFMember.mem")) {
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String text = request.getParameter("text");
				String answer = request.getParameter("answer");
				boolean result = influencerDAO.isMember(id, name, text, answer);
				response.getWriter().append(""+result);
				// 인플루언서 비밀번호 찾기
			}else if(cmd.equals("/influencerFindpw.mem")) {
				String id = request.getParameter("id_if");
				String name = request.getParameter("name_if");
				String text = request.getParameter("check-text-if");
				String answer = request.getParameter("answer-if");
				InfluencerDTO dto = influencerDAO.findMember(id, name, text, answer);
				System.out.println("회원 정보 검색 결과 : "+ dto.getId());
				request.setAttribute("ifdto", dto);
				request.getRequestDispatcher("/resources/login/findifpwDetail.jsp").forward(request, response);
				// 인플루언서 비밀번호 재설정
			}else if(cmd.equals("/resetIFpw.mem")) {
				String pw = sha512.generate(request.getParameter("pw_if"));
				String id = request.getParameter("id");
				System.out.println(id + "의 재설정한 비밀번호 : "+pw);
				int result = influencerDAO.updateNewPW(id, pw);
				System.out.println("비밀번호 재설정 결과 : "+result);
				response.sendRedirect("/resources/login/login.jsp");
				// 아이디 찾기 이동
			}else if(cmd.equals("/findid.mem")) {
				response.sendRedirect("/resources/login/findid.jsp");
				// 기업 아이디 맞는지 확인	
			}else if(cmd.equals("/isCPidExist.mem")) {
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String text = request.getParameter("text");
				String answer = request.getParameter("answer");
				CompanyDTO dto = companyDAO.findId(email, name, text, answer);
				System.out.println("기업 아이디는 : " + dto.getId());
				response.getWriter().append(dto.getId());
				// 인플루언서 아이디 맞는지 확인
			}else if(cmd.equals("/isIFidExist.mem")) {
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String text = request.getParameter("text");
				String answer = request.getParameter("answer");
				System.out.println(email+":"+name+":"+text+":"+answer);
				InfluencerDTO dto = influencerDAO.findId(email, name, text, answer);
				System.out.println("인플루언서 아이디는 : " + dto.getId());
				response.getWriter().append(dto.getId());
			}else if(cmd.equals("/IFSubmit.mem")) {
				
				String savePath = request.getServletContext().getRealPath("files");
				File filePath = new File(savePath);
				if(!filePath.exists()) {filePath.mkdir();}
				
				int maxSize = 1024*1024*10; // 10MB
				MultipartRequest multi2 = new MultipartRequest(request, savePath, maxSize, "UTF8", new DefaultFileRenamePolicy());
				
				String sysName = multi2.getFilesystemName("photo");
				String oriName = multi2.getOriginalFileName("photo");
				String id = multi2.getParameter("id");
				String pw = multi2.getParameter("pw");
				String name = multi2.getParameter("name");
				String nickName = multi2.getParameter("nickName");
				String zipcode = multi2.getParameter("zipcode");
				String address1 = multi2.getParameter("address1");
				String address2 = multi2.getParameter("address2");
				String sns = multi2.getParameter("sns");
				String phone = multi2.getParameter("phone");
				String email = multi2.getParameter("email");
				String grade = multi2.getParameter("grade");
				String pwAsk = multi2.getParameter("pwAsk");
				String pwAnswer = multi2.getParameter("pwAnswer");

				String favorite1 = multi2.getParameter("favorite1");
				String favorite2 = multi2.getParameter("favorite2");
				String favorite3 = multi2.getParameter("favorite3");
				String favorite4 = multi2.getParameter("favorite4");
				// company 테이블의 seq 생성
				int ifSeq = influencerDAO.createNewseq();
				
				int result = influencerDAO.insert(ifSeq,id, sha512.generate(pw), sysName, name, nickName, zipcode, address1, address2, sns, phone, email, grade, pwAsk, pwAnswer, favorite1 +":"+ favorite2 +":"+ favorite3 +":"+ favorite4);
				System.out.println("인플루언서 회원가입 결과 : "+result);
				// 프로필 사진 별도로 insert 
				int fileResult = fileDAO.insertIf(new FileDTO(0,oriName,sysName,ifSeq));
				System.out.println("인플루언서 프로필 사진 insert 결과 : " + fileResult);
				
				response.sendRedirect("/resources/login/login.jsp");

			}else if(cmd.equals("/IFidCheck.mem")) {

				String id = request.getParameter("id");
				boolean result = influencerDAO.isIdExist(id);
				response.getWriter().append(String.valueOf(result));

			}else if(cmd.equals("/IFnickCheck.mem")) {

				String nickName = request.getParameter("nickName");
				boolean result = influencerDAO.nickNameExist(nickName);
				response.getWriter().append(String.valueOf(result));

			}else if(cmd.equals("/mypage.mem")) {

				String id = (String)request.getSession().getAttribute("loginID");
				CompanyDTO cdto = companyDAO.selectById(id);
				InfluencerDTO idto = influencerDAO.selectById(id);

				if(cdto != null ) {
					request.setAttribute("dto", cdto);
					request.getRequestDispatcher("/resources/mypage/CPmypageMain.jsp").forward(request, response);

				}else {
					request.setAttribute("dto", idto);
					request.getRequestDispatcher("/resources/mypage/IFmypageMain.jsp").forward(request, response);
				}

			}else if(cmd.equals("/modify.mem")) {

				String id = (String)request.getSession().getAttribute("loginID");
				CompanyDTO cdto = companyDAO.selectById(id);
				InfluencerDTO idto = influencerDAO.selectById(id);

				if(cdto != null ) {
					request.setAttribute("dto", cdto);
					request.getRequestDispatcher("/resources/mypage/CPModify.jsp").forward(request, response);

				}else {
					request.setAttribute("dto", idto);
					request.getRequestDispatcher("/resources/mypage/IFModify.jsp").forward(request, response);
				}


			}else if(cmd.equals("/CPmodify.mem")) {

				CompanyDTO dto = new CompanyDTO();
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
				Long sales = Long.parseLong(request.getParameter("sales"));
				String pwAsk = request.getParameter("pwAsk");
				String pwAnswer = request.getParameter("pwAnswer");

				int result = companyDAO.update(sha512.generate(pw), photo, name, crunumber, zipcode, address1, address2, rpt_cp, phone, email, sales, pwAsk, pwAnswer, id);
				response.sendRedirect("/resources/mypage/CPmypageMain.jsp");

			}else if(cmd.equals("/IFmodify.mem")) {

				InfluencerDTO dto = new InfluencerDTO();
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String photo = request.getParameter("photo");
				String name = request.getParameter("name");
				String nickname = request.getParameter("nickName");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String sns = request.getParameter("sns");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String pwAsk = request.getParameter("pwAsk");
				String pwAnswer = request.getParameter("pwAnswer");
				String favorite1 = request.getParameter("favorite1");
				String favorite2 = request.getParameter("favorite2");
				String favorite3 = request.getParameter("favorite3");
				String favorite4 = request.getParameter("favorite4");

				int result = influencerDAO.update(sha512.generate(pw), photo, name, nickname, zipcode, address1, address2, sns, phone, email, pwAsk, pwAnswer, favorite1 +":"+ favorite2 +":"+ favorite3 +":"+ favorite4, id);
				response.sendRedirect("/resources/mypage/IFmypageMain.jsp");

			}else if(cmd.equals("/Ifprofile.mem")) {

				String id = (String)request.getSession().getAttribute("loginID");
				InfluencerDTO idto = influencerDAO.selectById(id);
				request.setAttribute("dto", idto);
				request.getRequestDispatcher("/resources/mypage/IFprofile.jsp").forward(request, response);

			}else if(cmd.equals("/upload.mem")) {
				
				String id = (String)request.getSession().getAttribute("loginID");
				InfluencerDTO idto = influencerDAO.selectById(id);
				request.setAttribute("dto", idto);
				
				request.getRequestDispatcher("/resources/mypage/IFprofile.jsp").forward(request, response);
				
			}else if(cmd.equals("/IFKkanbuList.mem")) {
				
				String id = (String)request.getSession().getAttribute("loginID");
				InfluencerDTO idto = influencerDAO.selectById(id);
				request.setAttribute("dto", idto);
				request.getRequestDispatcher("/resources/mypage/IFmypageKkanbu.jsp").forward(request, response);
				
			}else if(cmd.equals("/IFReviewList.mem")) {
				
				String id = (String)request.getSession().getAttribute("loginID");
				InfluencerDTO idto = influencerDAO.selectById(id);
				request.setAttribute("dto", idto);
				request.getRequestDispatcher("/resources/mypage/IFmypageReview.jsp").forward(request, response);
				
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
