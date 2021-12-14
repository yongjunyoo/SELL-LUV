package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.Board_CpDTO;
import kh.web.dto.InfluencerDTO;
import kh.web.dto.Profile_IfDTO;
import kh.web.statics.IFCPStatics;

public class InfluencerDAO  {

	private static InfluencerDAO instance = null;

	public static InfluencerDAO getInstance() {
		if(instance==null) {
			instance = new InfluencerDAO();
		}
		return instance;
	}
	public InfluencerDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}



	//====================================================================================================================================
	//로그인..
	public boolean login(String id, String pw) throws SQLException, Exception {
		String sql = "SELECT * FROM influencer WHERE id_if =? AND pw_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();){

				return rs.next();

			}
		}
	}
	//====================================================================================================================================
	//페이징...
	//개인 총 갯수..
	private int getRecordCount() throws SQLException, Exception {
		String sql = "select count(*) from influencer";

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
		return pageNavi;
	}

	public ArrayList<InfluencerDTO> selectByBound(int start, int end) throws Exception {
		//			String sql ="select * from (select influencer.*, row_number() over(order by seq_if desc) rn from influencer) where rn between ? and ?";
		String sql = "  SELECT * \n"
				+ "  from (select influencer.*, row_number() over(order by seq_if desc) rn,decode(grade,'gold','A','silver','B','bronze','C') sort_grade from influencer ORDER BY sort_grade) \n"
				+ "   where rn between ? and ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){;
				pstat.setInt(1, start);
				pstat.setInt(2, end);
				try(ResultSet rs = pstat.executeQuery()){

					List<InfluencerDTO> list = new ArrayList<>();

					while(rs.next()) {
						int seq = rs.getInt("seq_if");
						String id = rs.getString("id_if");
						String pw = rs.getString("pw_if");
						String photo = rs.getString("photo_if");
						String name = rs.getString("name_if");
						String nickname = rs.getString("nickname_if");
						String zipcode = rs.getString("zipcode_if");
						String address1 = rs.getString("address1_if");
						String address2 = rs.getString("address2_if");
						String sns = rs.getString("sns_if");
						String phone = rs.getString("phone_if");
						String email= rs.getString("email_if");
						String grade = rs.getString("grade");
							
						
						InfluencerDTO influencerDTO = new InfluencerDTO(seq,id,pw,photo,name,nickname,zipcode,address1,address2,sns,phone,email,grade);

						list.add(influencerDTO);
					}
					return (ArrayList<InfluencerDTO>) list;
				}
		}
	}

	public List<Profile_IfDTO> ifCardSearch(int seq) throws Exception { // 인플루언서 카드 작성자로 검색
		String sql = "select * from profile_if where seq_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				List<Profile_IfDTO> list = new ArrayList();
				if(rs.next()) {
					Profile_IfDTO dto = new Profile_IfDTO();
					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setWriter_if(rs.getString("writer_if"));
					dto.setPhoto_if(rs.getString("photo_if"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setSns_if(rs.getString("sns_if"));
					dto.setsLike_if(rs.getInt("sLike_if"));
					dto.setrLike_if(rs.getInt("rLike_if"));
					dto.setReview_if(rs.getString("review_if"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	// 회원가입 method
		public int insert(String id, String pw, String photo, String name, String nickName, String zipcode, String address1, 
				String address2, String sns, String phone, String email, String grade, String pwAsk, String pwAnswer, String favorite ) throws Exception {

			String sql = "insert into influencer values(seq_if.nextval,?,?,?,?,?,?,?,?,?,?,?,default,?,?,?)";


			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){

				pstat.setString(1, id);
				pstat.setString(2, pw);			
				pstat.setString(3, photo);
				pstat.setString(4, name);
				pstat.setString(5, nickName);
				pstat.setString(6, zipcode);
				pstat.setString(7, address1);
				pstat.setString(8, address2);
				pstat.setString(9, sns);
				pstat.setString(10, phone);
				pstat.setString(11, email);
				pstat.setString(12, pwAsk);
				pstat.setString(13, pwAnswer);
				pstat.setString(14, favorite);
				int result  = pstat.executeUpdate();

				return result;
			}
		}
		
		// 회원가입 중복 ID 체크 method
		public boolean isIdExist(String id) throws Exception{

			String sql = "select * from(select id_if from influencer union select id_cp from company) where id_if = ?";

			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1,id);
				try(ResultSet rs = pstat.executeQuery()){
					return rs.next();
				}		
			}
		}
		
		// 닉네임 중복 체크 method
		public boolean nickNameExist(String nickName) throws Exception{

			String sql = "select * from influencer where nickname_if = ?";

			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1,nickName);
				try(ResultSet rs = pstat.executeQuery()){
					return rs.next();
				}		
			}
		}
		
		public InfluencerDTO selectById(String paramID) throws Exception {

			String sql = "select * from influencer where id_if = ?";

			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, paramID);
				try(ResultSet rs = pstat.executeQuery()){

					InfluencerDTO dto = new InfluencerDTO();
					if(rs.next()) {
						dto.setSeq(rs.getInt("seq_if"));
						dto.setId(rs.getString("id_if"));
						dto.setPw(rs.getString("pw_if"));
						dto.setPhoto(rs.getString("photo_if"));
						dto.setName(rs.getString("name_if"));
						dto.setNickname(rs.getString("nickname_if"));
						dto.setZipcode(rs.getString("zipcode_if"));
						dto.setAddress1(rs.getString("address1_if"));
						dto.setAddress2(rs.getString("address2_if"));
						dto.setSns(rs.getString("sns_if"));
						dto.setPhone(rs.getString("phone_if"));
						dto.setEmail(rs.getString("email_if"));
						dto.setGrade(rs.getString("grade"));
						dto.setPwAsk(rs.getString("pwAsk_if"));
						dto.setPwAnswer(rs.getString("pwAnswer_if"));
						dto.setFavorite(rs.getString("favorite_if"));
						return dto;					
					}
					return null;
				}
			}
		}
		
		public int update(String pw, String photo, String name, String nickname, String zipcode, String address1, 
				String address2, String sns, String phone, String email, String pwAsk, String pwAnswer, String favorite, String id) throws Exception {
			String sql = "update influencer set pw_if = ?, photo_if = ?, name_if = ?, nickname_if = ?, zipcode_if = ?, address1_if = ?, "
					+ "address2_if = ?, sns_if = ?, phone_if = ?, email_if = ?, pwAsk_if = ?, pwAnswer_if = ?, favorite_if = ? where id_if = ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				
				pstat.setString(1, pw);			
				pstat.setString(2, photo);
				pstat.setString(3, name);
				pstat.setString(4, nickname);
				pstat.setString(5, zipcode);
				pstat.setString(6, address1);
				pstat.setString(7, address2);
				pstat.setString(8, sns);
				pstat.setString(9, phone);
				pstat.setString(10, email);
				pstat.setString(11, pwAsk);
				pstat.setString(12, pwAnswer);
				pstat.setString(13, favorite);				
				pstat.setString(14, id);
				int result  = pstat.executeUpdate();
				
				return result;
			}
		}
}
