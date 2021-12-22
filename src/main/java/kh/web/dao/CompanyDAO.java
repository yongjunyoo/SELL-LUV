package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.BoardDTO;
import kh.web.dto.Board_CpDTO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.FileDTO;
import kh.web.dto.Review_IfDTO;
import kh.web.statics.IFCPStatics;

public class CompanyDAO {

	private static CompanyDAO instance = null;

	public static CompanyDAO getInstance() {
		if(instance==null) {
			instance = new CompanyDAO();
		}
		return instance;
	}
	public CompanyDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	//====================================================================================================================================
	//로그인 
	public boolean login(String id, String pw) throws SQLException, Exception {
		String sql = "SELECT * FROM company WHERE id_cp =? AND pw_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();){

				boolean result = rs.next();

				return result;

			}
		}

	}

	//====================================================================================================================================
	//페이징 목록 출력시작....

	//기업 총 개수..
	private int getRecordCount() throws SQLException, Exception {
		String sql = "select count(*) from board_Cp";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			rs.next();
			return rs.getInt(1);
		}

	}

	//목록 출력 페이징 알고리즘..
	public String getPageNavi(int currentPage) throws SQLException, Exception {

		int recordTotalCount = this.getRecordCount();

		int pageTotalCount =0; //총페이지...

		if(recordTotalCount%IFCPStatics.RECORD_COUNT_PER_PAGE == 0) { //페이지수가 나눠떨어지는경우..
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE;
		}else { //페이지수가 안나눠떨어지는경우..
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE + 1;
		}

		if(currentPage < 1) { //페이지수가 1보다 작은경우..
			currentPage = 1;
		}else if(currentPage > pageTotalCount) { //총페이지 수가 현재페이지보다작은경우..
			currentPage = pageTotalCount;
		}

		int startNavi = (currentPage -1) / IFCPStatics.NAVI_PER_PAGE * 3 + 1; //10단위씩 끊어서 하단의 첫번째 수..
		int endNavi = startNavi + IFCPStatics.NAVI_PER_PAGE-1; //하단표시된 페이지 수중에마지막수.. 

		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount; 
		}

		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi == 1) { //첫페이지엔 이전페이지가 필요없
			needPrev = false;
		}

		if(endNavi == pageTotalCount) { //마지막 페이지에선 뒤페이지가 필요없
			needNext = false;
		}
		String pageNavi = ""; 

		if(needPrev) {
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyList.ifcp?cpage="+(startNavi-1)+"'>◀</a></li>";
		}
		for(int i = startNavi; i <=endNavi; i++) {
			pageNavi+="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyList.ifcp?cpage="+i+"'>"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyList.ifcp?cpage="+(endNavi+1)+"'>▶</a></li>";
		}
		System.out.println(endNavi);
		return pageNavi;
	}


	public LinkedHashMap<Board_CpDTO,CompanyDTO> selectByBound(int start, int end) throws Exception {
		//		String sql ="select * from (select company.*, row_number() over(order by seq_cp desc) rn from company) where rn between ? and ?";
		String sql = "select * from (\r\n"
				+ "				 select \r\n"
				+ "               row_number() over(order by seq_cp desc) rn, \r\n"
				+ "				    temp.*\r\n"
				+ "				from \r\n"
				+ "                (select \r\n"
				+ "				 c.*,\r\n"
				+ "				seq_board_cp,\r\n"
				+ "				member_seq,\r\n"
				+ "				title_cp,\r\n"
				+ "				condition_cp,\r\n"
				+ "				intro_cp,\r\n"
				+ "				slike_cp,\r\n"
				+ "				rlike_cp,\r\n"
				+ "				 b.photo_cp b_photo_cp from company c,board_cp b where seq_cp = member_seq) temp)\r\n"
				+ "				 where rn between ? and ? order by 2 desc";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){;
				pstat.setInt(1, start);
				pstat.setInt(2, end);
				try(ResultSet rs = pstat.executeQuery()){

					//					List<CompanyDTO> list = new ArrayList<>();
					LinkedHashMap<Board_CpDTO,CompanyDTO> list = new LinkedHashMap<>();

					while(rs.next()) {
						int seq = rs.getInt("seq_cp");
						String id = rs.getString("id_cp");
						String pw = rs.getString("pw_cp");
						String photo = rs.getString("photo_cp");
						String name = rs.getString("name_cp");
						String crnumber = rs.getString("crnumber_cp");
						String zipcode = rs.getString("zipcode_cp");
						String address1 = rs.getString("address1_cp");
						String address2 = rs.getString("address2_cp");
						String rpt = rs.getString("rpt_cp");
						String phone = rs.getString("phone_cp");
						String email= rs.getString("email_cp");
						Long sales = rs.getLong("sales_cp");
						String grade = rs.getString("grade");
						String pwAsk = rs.getString("pwAsk_cp");
						String pwAnswer = rs.getString("pwAnswer_cp");


						int seq_board_cp = rs.getInt("seq_board_cp");
						int member_seq = rs.getInt("member_seq");
						String title_cp = rs.getString("title_cp");
						String condition_cp = rs.getString("condition_cp");
						String intro_cp = rs.getString("intro_cp");
						int sLike_cp =  rs.getInt("sLike_cp");
						int rLike_cp =  rs.getInt("rLike_cp");
						String photo_cp = rs.getString("photo_cp");


						CompanyDTO companyDTO = new CompanyDTO(seq,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,pwAsk,pwAnswer);
						Board_CpDTO board_CpDTO = new Board_CpDTO(seq_board_cp,member_seq,title_cp,condition_cp,intro_cp,sLike_cp,rLike_cp,photo_cp);

						list.put(board_CpDTO,companyDTO);
					}
					return list;
				}
		}
	}

	//페이징 목록 출력 끝..
	//====================================================================================================================================

	public LinkedHashMap<Board_CpDTO, CompanyDTO> getCompanyBoardDetail(int seq) throws Exception { 
		String sql = " select c.*, b.* from company c\n"
				+ "   join board_cp b ON c.seq_cp = b.member_seq\n"
				+ "   where seq_board_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){

				LinkedHashMap<Board_CpDTO,CompanyDTO> list = new LinkedHashMap<>();

				while(rs.next()) {

					seq = rs.getInt("seq_cp");
					String id = rs.getString("id_cp");
					String pw = rs.getString("pw_cp");
					String photo = rs.getString("photo_cp");
					String name = rs.getString("name_cp");
					String crnumber = rs.getString("crnumber_cp");
					String zipcode = rs.getString("zipcode_cp");
					String address1 = rs.getString("address1_cp");
					String address2 = rs.getString("address2_cp");
					String rpt = rs.getString("rpt_cp");
					String phone = rs.getString("phone_cp");
					String email= rs.getString("email_cp");
					Long sales = rs.getLong("sales_cp");
					String grade = rs.getString("grade");
					String pwAsk = rs.getString("pwAsk_cp");
					String pwAnswer = rs.getString("pwAnswer_cp");

					int seq_board_cp = rs.getInt("seq_board_cp");
					int member_seq = rs.getInt("member_seq");
					String title_cp = rs.getString("title_cp");
					String condition_cp = rs.getString("condition_cp");
					String intro_cp = rs.getString("intro_cp");
					int sLike_cp =  rs.getInt("sLike_cp");
					int rLike_cp =  rs.getInt("rLike_cp");
					String photo_cp = rs.getString("photo_cp");

					CompanyDTO companyDTO = new CompanyDTO(seq,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,pwAsk,pwAnswer);
					Board_CpDTO board_CpDTO = new Board_CpDTO(seq_board_cp,member_seq,title_cp,condition_cp,intro_cp,sLike_cp,rLike_cp,photo_cp);

					list.put(board_CpDTO,companyDTO);
					Board_CpDTO dto = new Board_CpDTO();
					dto.setSeq_cp(rs.getInt("seq_board_cp"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setTitle_cp(rs.getString("title_cp"));
					dto.setCondition_cp(rs.getString("condition_cp"));
					dto.setIntro_cp(rs.getString("intro_cp"));
					dto.setsLike_cp(rs.getInt("sLike_cp"));
					dto.setrLike_cp(rs.getInt("rLike_cp"));
					dto.setPhoto_cp(rs.getString("photo_cp"));
					list.put(board_CpDTO,companyDTO);

				}
				return list;
			}
		}
	}	

	// company_seq 생성 메소드 
	public int createNewseq() throws Exception {
		String sql = "SELECT company_seq_cp.NEXTVAL FROM dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){
			rs.next();
			return rs.getInt(1);
		}
	}


	// Company 회원가입 -이준협

	// 회원가입 method
	public int insert(int seq, String id, String pw, String photo, String name, String crunumber, String zipcode, String address1, 
			String address2, String rpt_cp, String phone, String email, String sales, String grade, String pwAsk, String pwAnswer) throws Exception {

		String sql = "insert into company values(?,?,?,?,?,?,?,?,?,?,?,?,?,default,?,?)";


		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			pstat.setString(2, id);
			pstat.setString(3, pw);			
			pstat.setString(4, photo);
			pstat.setString(5, name);
			pstat.setString(6, crunumber);
			pstat.setString(7, zipcode);
			pstat.setString(8, address1);
			pstat.setString(9, address2);
			pstat.setString(10, rpt_cp);
			pstat.setString(11, phone);
			pstat.setString(12, email);
			pstat.setString(13, sales);
			pstat.setString(14, pwAsk);
			pstat.setString(15, pwAnswer);
			int result  = pstat.executeUpdate();

			return result;
		}
	}

	// 회원가입 중복 ID 체크 method
	public boolean isIdExist(String id) throws Exception{

		String sql = "select * from(select id_cp from company union select id_if from influencer) where id_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,id);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}		
		}
	}

	// 아이디 정보 전달 method
	public CompanyDTO selectById(String paramID) throws Exception {

		String sql = "select * from company where id_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, paramID);
			try(ResultSet rs = pstat.executeQuery()){

				CompanyDTO dto = new CompanyDTO();
				if(rs.next()) {
					dto.setSeq(rs.getInt("seq_cp"));
					dto.setId(rs.getString("id_cp"));
					dto.setPw(rs.getString("pw_cp"));
					dto.setPhoto(rs.getString("photo_cp"));
					dto.setName(rs.getString("name_cp"));
					dto.setCrnumber(rs.getString("crnumber_cp"));
					dto.setZipcode(rs.getString("zipcode_cp"));
					dto.setAddress1(rs.getString("address1_cp"));
					dto.setAddress2(rs.getString("address2_cp"));
					dto.setRpt(rs.getString("rpt_cp"));
					dto.setPhone(rs.getString("phone_cp"));
					dto.setEmail(rs.getString("email_cp"));
					dto.setSales(rs.getLong("sales_cp"));
					dto.setGrade(rs.getString("grade"));
					dto.setPwAsk(rs.getString("pwAsk_cp"));
					dto.setPwAnswer(rs.getString("pwAnswer_cp"));
					return dto;					
				}
				return null;
			}
		}
	}

	public int update(String pw, String photo, String name, String crunumber, String zipcode, String address1, 
			String address2, String rpt_cp, String phone, String email, String sales, String pwAsk, String pwAnswer, String id) throws Exception {

		String sql = "update company set pw_cp = ?, photo_cp = ?, name_cp = ?, crnumber_cp = ?, zipcode_cp = ?, address1_cp = ?, address2_cp = ?, rpt_cp = ?, phone_cp = ?, email_cp = ?, sales_cp = ?, pwAsk_cp = ?, pwAnswer_cp =? where id_cp = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, pw);			
			pstat.setString(2, photo);
			pstat.setString(3, name);
			pstat.setString(4, crunumber);
			pstat.setString(5, zipcode);
			pstat.setString(6, address1);
			pstat.setString(7, address2);
			pstat.setString(8, rpt_cp);
			pstat.setString(9, phone);
			pstat.setString(10, email);
			pstat.setString(11, sales);
			pstat.setString(12, pwAsk);
			pstat.setString(13, pwAnswer);
			pstat.setString(14, id);
			int result  = pstat.executeUpdate();

			return result;
		}
	}




	// 맞는 회원 정보 가져오기
	public CompanyDTO findMember(String id, String name, String text, String answer) throws Exception {
		String sql = "SELECT * FROM company WHERE id_cp =? AND name_cp =? AND pwask_cp =? AND pwanswer_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, name);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				CompanyDTO dto = new CompanyDTO();
				if(rs.next()) {
					int seq = rs.getInt("seq_cp");
					String pw = rs.getString("pw_cp");
					String photo = rs.getString("photo_cp");
					String crnumber = rs.getString("crnumber_cp");
					String zipcode = rs.getString("zipcode_cp");
					String address1 = rs.getString("address1_cp");
					String address2 = rs.getString("address2_cp");
					String rpt = rs.getString("rpt_cp");
					String phone = rs.getString("phone_cp");
					String email= rs.getString("email_cp");
					Long sales = rs.getLong("sales_cp");
					String grade = rs.getString("grade");

					dto = new CompanyDTO(seq,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,text,answer);
				}
				return dto;
			}
		}
	}

	// 맞는 회원 정보 가져오기
	public CompanyDTO findId(String email, String name, String text, String answer) throws Exception {
		String sql = "SELECT * FROM company WHERE email_cp =? AND name_cp =? AND pwask_cp =? AND pwanswer_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, email);
			pstat.setString(2, name);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				CompanyDTO dto = new CompanyDTO();
				if(rs.next()) {
					int seq = rs.getInt("seq_cp");
					String pw = rs.getString("pw_cp");
					String id = rs.getString("id_cp");
					String photo = rs.getString("photo_cp");
					String crnumber = rs.getString("crnumber_cp");
					String zipcode = rs.getString("zipcode_cp");
					String address1 = rs.getString("address1_cp");
					String address2 = rs.getString("address2_cp");
					String rpt = rs.getString("rpt_cp");
					String phone = rs.getString("phone_cp");
					Long sales = rs.getLong("sales_cp");
					String grade = rs.getString("grade");

					dto = new CompanyDTO(seq,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,text,answer);
				}
				return dto;
			}
		}
	}

	// 멤버인지 아닌지 확인하는 메소드
	public boolean isMember(String id, String name, String text, String answer) throws Exception {
		String sql = "SELECT * FROM company WHERE id_cp =? AND name_cp =? AND pwask_cp =? AND pwanswer_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, name);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				CompanyDTO dto = new CompanyDTO();
				if(rs.next()) {
					return true;
				}
				return false;
			}
		}
	}

	public boolean isMember(String id) throws Exception {
		String sql = "SELECT * FROM company WHERE id_cp =? ";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){

				if(rs.next()) {
					return true;
				}
				return false;
			}
		}
	}

	// 비밀번호 재설정 메소드

	public int updateNewPW(String id, String pw) throws Exception {
		String sql = "UPDATE company SET pw_cp=? WHERE id_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, pw);
			pstat.setString(2, id);			
			int result  = pstat.executeUpdate();

			return result;
		} 
	}


	// 제품등록시 정보 불러오기
	public ArrayList<CompanyDTO> searchById(String loginID) throws Exception {
		String sql = "select * from company where id_cp=?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){;
				pstat.setString(1, loginID);
				try(ResultSet rs = pstat.executeQuery()){

					List<CompanyDTO> list = new ArrayList<>();

					while(rs.next()) {
						int seq1 = rs.getInt("seq_cp");
						String id = rs.getString("id_cp");
						String pw = rs.getString("pw_cp");
						String photo = rs.getString("photo_cp");
						String name = rs.getString("name_cp");
						String crnumber = rs.getString("crnumber_cp");
						String zipcode = rs.getString("zipcode_cp");
						String address1 = rs.getString("address1_cp");
						String address2 = rs.getString("address2_cp");
						String rpt = rs.getString("rpt_cp");
						String phone = rs.getString("phone_cp");
						String email= rs.getString("email_cp");
						Long sales = rs.getLong("sales_cp");
						String grade = rs.getString("grade");
						String pwAsk = rs.getString("pwAsk_cp");
						String pwAnswer = rs.getString("pwAnswer_cp");


						CompanyDTO companyDTO = new CompanyDTO(seq1,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,pwAsk,pwAnswer);


						list.add(companyDTO);
					}
					return (ArrayList<CompanyDTO>) list;
				}
		}
	}

	public int insertPhoto(FileDTO dto) throws Exception { // 사진 업로드
		String sql = "insert into file_product values(file_product_seq.nextval,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParentSeq());
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}

	public int writeIntro(int seq,String title,String intro,String condition) throws Exception { // 글 업로드
		String sql = "insert into board_cp (seq_board_cp, member_seq, title_cp, intro_cp, condition_cp) values(board_cp_seq.nextval,?,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			pstat.setString(2, title);
			pstat.setString(3, intro);
			pstat.setString(4, condition);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}


	public int cpSearchById(String loginID) throws Exception{ // 로그인id로 seq_cp추출.
		String sql = "select seq_cp from company where id_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, loginID);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_cp");
				}
			}
			return result;
		}
	}

	public int findSeq(String id, String pw) throws Exception{
		String sql = "SELECT seq_cp FROM company WHERE id_cp =? AND pw_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_cp");

				}
			}
			return result;
		}
	}

	public String getName(int kkanbuSeqFrom) throws SQLException, Exception {
		String sql = "SELECT name_cp FROM company WHERE seq_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeqFrom);
			String result = "";
			
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("name_cp");
					
					return result;
				}
			}
			
			return result;
		}
	}

	public int findSeq(String id) throws Exception{
		String sql = "SELECT seq_cp FROM company WHERE id_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_cp");

				}
			}
			return result;
		}
	}

	public String findName(String id, String pw) throws Exception{
		String sql = "SELECT name_cp FROM company WHERE id_cp =? AND pw_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);

			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {

					result = rs.getString("name_cp");



				}
			}
			return result;
		}
	}

	public String findName(String id) throws Exception{
		String sql = "SELECT name_cp FROM company WHERE id_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("name_cp");
				}
			}
			return result;
		}
	}



	public String findProfile(String id) throws Exception{
		String sql = "SELECT photo_cp FROM company WHERE id_cp = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("photo_cp");

				}
			}
			return result;
		}
	}

	public List<Review_IfDTO> ifReview() throws Exception{ // 인플루언서가 작성한 리뷰
		String sql = "select * from review_if";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery()){

				List<Review_IfDTO> list = new ArrayList<>();

				while(rs.next()) {
					int seq = rs.getInt("seq");
					int member_seq = rs.getInt("member_seq");
					String writer = rs.getString("writer");
					String content = rs.getString("content");
					Timestamp timestamp = rs.getTimestamp("timestamp");
					int ref_seq = rs.getInt("ref_seq");

					Review_IfDTO dto = new Review_IfDTO(seq,member_seq,writer,content,timestamp,ref_seq);

					list.add(dto);
				}
				return list;
			}
		}
	}

	public int getIfCardCount(int seq) throws Exception { // 인플루언서가 작성한 리뷰 수 출력.
		String sql = "select count(*) from review_if where member_seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}

	public int getifCardPageTotalCount(int seq) throws Exception { // 기업 페이지
		int recordTotalCount = this.getIfCardCount(seq);

		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%IFCPStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}

	public String getifCardPageNavi(int currentPage,int seq) throws Exception { // 기업 네비
		int recordTotalCount = this.getIfCardCount(seq);

		int pageTotalCount = 0;
		if(recordTotalCount%IFCPStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/IFCPStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/IFCPStatics.NAVI_PER_PAGE*IFCPStatics.NAVI_PER_PAGE+1;
		int endNavi = startNavi+IFCPStatics.NAVI_PER_PAGE-1;

		if(endNavi > pageTotalCount) {  
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi==1) {
			needPrev = false;
		}
		if(endNavi==pageTotalCount) {
			needNext = false;
		}

		String pageNavi ="";
		if(needPrev) {
			pageNavi +="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyBoard.ifcp?seq="+seq+"&cpage="+(startNavi-1)+"'>◀</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyBoard.ifcp?seq="+seq+"&cpage="+i+"'>"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/companyBoard.ifcp?seq="+seq+"&cpage="+(endNavi+1)+"'>▶</a></li>";
		}

		return pageNavi;
	}

	public List<Review_IfDTO> ifCardBoundary(int seq,int start, int end) throws Exception { // 9개씩 뽑아오는 코드.
		String sql = "select * from (select review_if.*, row_number() over(order by seq desc) rn from review_if where member_seq=?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			pstat.setInt(2, start);
			pstat.setInt(3, end);

			try(ResultSet rs = pstat.executeQuery();){
				List<Review_IfDTO> list = new ArrayList();
				while(rs.next()) {
					int seq1 = rs.getInt("seq");
					int member_seq = rs.getInt("member_seq");
					String writer = rs.getString("writer");
					String content = rs.getString("content");
					Timestamp timestamp = rs.getTimestamp("timestamp");
					int ref_seq = rs.getInt("ref_seq");

					Review_IfDTO dto = new Review_IfDTO(seq1,member_seq,writer,content,timestamp,ref_seq);

					list.add(dto);
				}
				return list;
			}
		}
	}

	// 기업상호명 중복 체크 method
	public boolean nameExist(String name) throws Exception{

		String sql = "select * from(select name_cp from company union select nickname_if from influencer) where name_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,name);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}		
		}
	}

	// 기업 회원 탈퇴
	public int delete(String id) throws Exception{
		String sql = "delete from company where id_cp = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			return result;
		}

	}


	// sysname추출을 위해 board_cp의 currVal 추출.
	public int createProductSeq() throws Exception {
		String sql = "SELECT board_cp_seq.currval FROM dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){
			rs.next();
			return rs.getInt(1);
		}
	}

	// 제품등록된 사진의 부모시퀀스를 통해 sysname 추출.
	public String productFindbySeq(String pSeq) throws Exception{
		String sql = "SELECT sysname_product_file from file_product WHERE parentseq_product_file = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, pSeq);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("sysname_product_file");
				}
			}
			return result;
		}
	}


	public int insertReview(int seq,String loginID,String review,int seq_if) throws Exception{ // 리뷰작성.
		String sql = "insert into review_if values(review_cp_seq.nextval,?,?,?,sysdate,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			pstat.setString(2, loginID);
			pstat.setString(3, review);
			pstat.setInt(4, seq_if);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	public int findCpSeq(int seq_cp) throws Exception{ // 기업 시퀀스로 기업 제품등록 시퀀스 찾기.
		String sql = "SELECT seq_board_cp FROM board_cp WHERE member_seq =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq_cp);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_board_cp");
				}
			}
			return result;
		}
	}
	
	public int cpDelete(String seq) throws Exception{ // 기업 제품등록 삭제.
		String sql = "delete from board_cp where seq_board_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setString(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			return result;
		}
	}
	
	// seq를 통해 기업 사진의 부모seq추출.
	public String ifFindbySeq(String seq) throws Exception{
		String sql = "SELECT seq_board_cp from board_cp WHERE member_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, seq);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("seq_board_cp");

				}
			}
			return result;
		}
	}
	
	public int findRealCpSeq(int seq_cp) throws Exception{ // 제품등록 시퀀스로 기업 시퀀스 찾기.
		String sql = "SELECT member_seq FROM board_cp WHERE seq_board_cp =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq_cp);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("member_seq");
				}
			}
			return result;
		}
	}
	
	public List<Board_CpDTO> findBoard_CpBySeq(String title_cp,int seq_cp) throws Exception{ // 기업 시퀀스로 board_cp불러오기.
		String sql = "select * from board_cp where title_cp=? and member_seq=?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, title_cp);
			pstat.setInt(2, seq_cp);
			try(ResultSet rs = pstat.executeQuery();){
				List<Board_CpDTO> list = new ArrayList();
				while(rs.next()) {
					int seq_board_cp = rs.getInt("seq_board_cp");
					int member_seq = rs.getInt("member_seq");
					String title_cp1 = rs.getString("title_cp");
					String condition_cp = rs.getString("condition_cp");
					String intro_cp = rs.getString("intro_cp");
					int sLike_cp = rs.getInt("sLike_cp");
					int rLike_cp = rs.getInt("rLike_cp");
					String photo_cp = rs.getString("photo_cp");
					Board_CpDTO board_CpDTO = new Board_CpDTO(seq_board_cp,member_seq,title_cp1,condition_cp,intro_cp,sLike_cp,rLike_cp,photo_cp);
					list.add(board_CpDTO);
				}
				return list;
			}
		}
	}
	
	public int modifyIntro(String title,String intro,String condition,String seq_board_cp) throws Exception { // 제품 수정
		String sql = "update board_cp set title_cp=?, intro_cp=?, condition_cp=? where seq_board_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, title);
			pstat.setString(2, intro);
			pstat.setString(3, condition);
			pstat.setString(4, seq_board_cp);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public int modifyPhoto(String oriName,String sysName,int productSeq) throws Exception { // 사진 수정 업로드
		String sql = "update file_product set oriname_product_file=?, sysname_product_file=? where parentseq_product_file=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, oriName);
			pstat.setString(2, sysName);
			pstat.setInt(3, productSeq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public int findCpSeqBySeqNTitle(String title_cp,int seq_cp) throws Exception{ // 기업 시퀀스로 기업 제품등록 시퀀스 찾기.
		String sql = "SELECT seq_board_cp FROM board_cp WHERE title_cp=? and member_seq =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, title_cp);
			pstat.setInt(2, seq_cp);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_board_cp");
				}
			}
			return result;
		}
	}
	public boolean didEnroll(int seq) throws Exception{

		String sql = "select * from board_cp where member_seq = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1,seq);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}		
		}
	}
	
}



