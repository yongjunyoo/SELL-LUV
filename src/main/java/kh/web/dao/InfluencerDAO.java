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

import kh.web.dto.InfluencerDTO;
import kh.web.dto.KkanbuDTO;
import kh.web.dto.Profile_IfDTO;
import kh.web.dto.Review_CpDTO;
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
		String sql = "select count(*) from profile_if";

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
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerList.ifcp?cpage="+(startNavi-1)+"'>◀</a></li>";
		}
		for(int i = startNavi; i <=endNavi; i++) {
			pageNavi+="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerList.ifcp?cpage="+i+"'>"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerList.ifcp?cpage="+(endNavi+1)+"'>▶</a></li>";
		}
		System.out.println(recordTotalCount+" " + startNavi+" " + endNavi);
		return pageNavi;
	}

	public LinkedHashMap<Profile_IfDTO, InfluencerDTO> selectByBound(int start, int end) throws Exception {

		String sql = "select * from (\n"
				+ " select \n"
				+ "    row_number() over(order by decode(grade,'gold','A','silver','B','bronze','C')) rn, \n"
				+ "    temp.*\n"
				+ "from \n"
				+ "    (select \n"
				+ "        i.id_if,\n"
				+ "        i.grade,\n"
				+ "        i.favorite_if,\n"
				+ "        i.sns_if,\n"
				+ "        i.name_if,\n"
				+ "        i.photo_if,\n"
				+ "        i.nickname_if,\n"
				+ "        i.phone_if,\n"
				+ "        i.email_if,\n"
				+ "        p.seq_if,\n"
				+ "        p.member_seq,\n"
				+ "        p.condition_if,\n"
				+ "        p.career_if,\n"
				+ "        p.intro_if,\n"
				+ "        p.slike_if,\n"
				+ "        p.rlike_if from influencer i, profile_if p where i.seq_if = member_seq) temp)\n"
				+ "        where rn between ? and ? order by 2 desc"
				+ " ";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){;
				pstat.setInt(1, start);
				pstat.setInt(2, end);
				try(ResultSet rs = pstat.executeQuery()){

					LinkedHashMap<Profile_IfDTO, InfluencerDTO> list = new LinkedHashMap<Profile_IfDTO, InfluencerDTO>();

					while(rs.next()) {
						//						int seq = rs.getInt("seq_if");
						String id = rs.getString("id_if");
						//						String pw = rs.getString("pw_if");
						String photo = rs.getString("photo_if");
						String name = rs.getString("name_if");
						String nickname = rs.getString("nickname_if");
						//						String zipcode = rs.getString("zipcode_if");
						//						String address1 = rs.getString("address1_if");
						//						String address2 = rs.getString("address2_if");
						String sns = rs.getString("sns_if");
						String phone = rs.getString("phone_if");
						String email= rs.getString("email_if");
						String grade = rs.getString("grade");



						//		InfluencerDTO influencerDTO = new InfluencerDTO(seq,id,pw,photo,name,nickname,zipcode,address1,address2,sns,phone,email,grade);

						//						String pwAsk = rs.getString("pwAsk_if");
						//
						//						String pwAnswer= rs.getString("pwAnswer_if");
						String favorite = rs.getString("favorite_if");

						int seq_if = rs.getInt("seq_if");
						int member_seq = rs.getInt("member_seq");
						String condition_if = rs.getString("condition_if");
						String career_if = rs.getString("condition_if");
						String intro_if = rs.getString("intro_if");
						int sLike_if = rs.getInt("sLike_if");
						int rLike_if = rs.getInt("rLike_if");



						InfluencerDTO influencerDTO = new InfluencerDTO(0,id,"",photo,name,nickname,"","","",sns,phone,email,grade,"","",favorite);
						Profile_IfDTO profile_IfDTO = new Profile_IfDTO(seq_if,member_seq,condition_if,career_if,intro_if,sLike_if,rLike_if);

						list.put(profile_IfDTO,influencerDTO);


					}
					return list;
				}
		}
	}
	//====================================================================================================================================

	public LinkedHashMap<Profile_IfDTO, InfluencerDTO> getIfProfile(int seq) throws Exception { // 인플루언서 카드 작성자로 검색..

		String sql = "select\n"
				+ " i.id_if,\n"
				+ " i.grade,\n"
				+ "        i.favorite_if,\n"
				+ "        i.sns_if,\n"
				+ "        i.name_if,\n"
				+ "        i.nickname_if,\n"
				+ "        i.phone_if,\n"
				+ "        i.email_if,\n"
				+ "        i.photo_if,\n"
				+ "        p.seq_if,\n"
				+ "        p.member_seq,\n"
				+ "        p.condition_if,\n"
				+ "        p.career_if,\n"
				+ "        p.intro_if,\n"
				+ "        p.slike_if,\n"
				+ "        p.rlike_if  from influencer i\n"
				+ "							   join profile_if p ON i.seq_if = p.member_seq\n"
				+ "						   where p.seq_if = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){

				LinkedHashMap<Profile_IfDTO, InfluencerDTO> list = new LinkedHashMap<Profile_IfDTO, InfluencerDTO>();

				while(rs.next()) {

					String id = rs.getString("id_if");
					//						String pw = rs.getString("pw_if");
					String photo = rs.getString("photo_if");
					String name = rs.getString("name_if");
					String nickname = rs.getString("nickname_if");
					//						String zipcode = rs.getString("zipcode_if");
					//						String address1 = rs.getString("address1_if");
					//						String address2 = rs.getString("address2_if");
					String sns = rs.getString("sns_if");
					String phone = rs.getString("phone_if");
					String email= rs.getString("email_if");
					String grade = rs.getString("grade");



					//		InfluencerDTO influencerDTO = new InfluencerDTO(seq,id,pw,photo,name,nickname,zipcode,address1,address2,sns,phone,email,grade);

					//						String pwAsk = rs.getString("pwAsk_if");
					//
					//						String pwAnswer= rs.getString("pwAnswer_if");
					String favorite = rs.getString("favorite_if");

					int seq_if = rs.getInt("seq_if");
					int member_seq = rs.getInt("member_seq");
					String condition_if = rs.getString("condition_if");
					String career_if = rs.getString("career_if");
					String intro_if = rs.getString("intro_if");
					int sLike_if = rs.getInt("sLike_if");
					int rLike_if = rs.getInt("rLike_if");



					//					InfluencerDTO influencerDTO = new InfluencerDTO(0,id,pw,photo,name,nickname,zipcode,address1,address2,sns,phone,email,grade,pwAsk,pwAnswer,favorite);
					//					Profile_IfDTO profile_IfDTO = new Profile_IfDTO(seq_if,member_seq,condition_if,career_if,intro_if,sLike_if,rLike_if);
					InfluencerDTO influencerDTO = new InfluencerDTO(0,id,"",photo,name,nickname,"","","",sns,phone,email,grade,"","",favorite);
					Profile_IfDTO profile_IfDTO = new Profile_IfDTO(seq_if,member_seq,condition_if,career_if,intro_if,sLike_if,rLike_if);

					list.put(profile_IfDTO,influencerDTO);

				}
				return list;
			}
		}
	}

	// influencer_seq 생성 메소드 
	public int createNewseq() throws Exception {
		String sql = "SELECT seq_if.nextval FROM dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){
			rs.next();
			return rs.getInt(1);
		}
	}


	// 회원가입 method
	public int insert(int seq, String id, String pw, String photo, String name, String nickName, String zipcode, String address1, 
			String address2, String sns, String phone, String email, String grade, String pwAsk, String pwAnswer, String favorite ) throws Exception {

		String sql = "insert into influencer values(?,?,?,?,?,?,?,?,?,?,?,?,default,?,?,?)";


		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			pstat.setString(2, id);
			pstat.setString(3, pw);			
			pstat.setString(4, photo);
			pstat.setString(5, name);
			pstat.setString(6, nickName);
			pstat.setString(7, zipcode);
			pstat.setString(8, address1);
			pstat.setString(9, address2);
			pstat.setString(10, sns);
			pstat.setString(11, phone);
			pstat.setString(12, email);
			pstat.setString(13, pwAsk);
			pstat.setString(14, pwAnswer);
			pstat.setString(15, favorite);
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

		String sql = "select * from(select nickname_if from influencer union select name_cp from company) where nickname_if = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,nickName);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}		
		}
	}


	// 아이디 정보 전달 method
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

	// 맞는 회원 정보 가져오기
	public InfluencerDTO findMember(String id, String pname, String text, String answer) throws Exception {
		String sql = "SELECT * FROM influencer WHERE id_if =? AND name_if =? AND pwask_if =? AND pwanswer_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pname);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				InfluencerDTO dto = new InfluencerDTO();
				if(rs.next()) {
					int seq = rs.getInt("seq_if");
					String pw = rs.getString("pw_if");
					String photo = rs.getString("photo_if");
					String name = rs.getString("name_if");
					String nickName = rs.getString("nickname_if");
					String zipcode = rs.getString("zipcode_if");
					String address1 = rs.getString("address1_if");
					String address2 = rs.getString("address2_if");
					String sns = rs.getString("sns_if");
					String phone = rs.getString("phone_if");
					String email= rs.getString("email_if");
					String grade = rs.getString("grade");
					String favorite = rs.getString("favorite_if");

					dto = new InfluencerDTO(seq,id,pw,photo,name,nickName,zipcode,address1,address2,sns,phone,email,grade,text,answer,favorite);
				}
				return dto;
			}
		}
	}


	// 마이페이지 인플루언서 회원정보 수정
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
	// 맞는 회원 정보 가져오기
	public InfluencerDTO findId(String email, String pname, String text, String answer) throws Exception {
		String sql = "SELECT * FROM influencer WHERE email_if =? AND name_if =? AND pwask_if =? AND pwanswer_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, email);
			pstat.setString(2, pname);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				InfluencerDTO dto = new InfluencerDTO();
				if(rs.next()) {
					int seq = rs.getInt("seq_if");
					String id = rs.getString("id_if");
					String pw = rs.getString("pw_if");
					String photo = rs.getString("photo_if");
					String name = rs.getString("name_if");
					String nickName = rs.getString("nickname_if");
					String zipcode = rs.getString("zipcode_if");
					String address1 = rs.getString("address1_if");
					String address2 = rs.getString("address2_if");
					String sns = rs.getString("sns_if");
					String phone = rs.getString("phone_if");
					String grade = rs.getString("grade");
					String favorite = rs.getString("favorite_if");

					dto = new InfluencerDTO(seq,id,pw,photo,name,nickName,zipcode,address1,address2,sns,phone,email,grade,text,answer,favorite);
				}
				return dto;
			}
		}
	}

	// 멤버인지 아닌지 확인하는 메소드
	public boolean isMember(String id, String name, String text, String answer) throws Exception {
		String sql = "SELECT * FROM influencer WHERE id_if =? AND name_if =? AND pwask_if =? AND pwanswer_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, name);
			pstat.setString(3, text);
			pstat.setString(4, answer);
			try(ResultSet rs = pstat.executeQuery();){

				InfluencerDTO dto = new InfluencerDTO();
				if(rs.next()) {
					return true;
				}
				return false;
			}
		}

	}

	public boolean isMember(String id) throws Exception {
		String sql = "SELECT * FROM influencer WHERE id_if =? ";
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
		String sql = "UPDATE influencer SET pw_if=? WHERE id_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, pw);
			pstat.setString(2, id);			
			int result  = pstat.executeUpdate();
			return result;
		}
	}


	//시퀀스찾기....
	public int findSeq(String id, String pw) throws Exception{
		String sql = "SELECT seq_if FROM influencer WHERE id_if =? AND pw_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_if");

				}
			}
			return result;
		}
	}

	public String findName(String id) throws Exception{
		String sql = "SELECT nickname_if FROM influencer WHERE id_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("nickname_if");
				}
			}
			return result;
		}
	}

	//시퀀스찾기....2
	public int findSeq(String id) throws Exception{
		String sql = "SELECT seq_if FROM influencer WHERE id_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_if");

				}
			}
			return result;
		}
	}

	// 닉네임 찾기
	public String findName(String id, String pw) throws Exception{
		String sql = "SELECT nickname_if FROM influencer WHERE id_if =? AND pw_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("nickname_if");

				}
			}
			return result;
		}
	}

	public String findProfile(String id) throws Exception{
		String sql = "SELECT photo_if FROM influencer WHERE id_if = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("photo_if");

				}
			}
			return result;
		}
	}


	public String getNickname(int kkanbuSeqFrom) throws Exception {
		String sql = "SELECT nickname_if FROM influencer WHERE seq_if =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeqFrom);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("nickname_if");
				}
			}
			return result;
		}
	}




	// 인플루언서 프로필 생성

	public int insertProfile(String seq, String condition, String career, String intro) throws Exception{

		String sql = "insert into profile_if values(profile_if_seq_if.nextval,?,?,?,?,null,null)";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, seq);
			pstat.setString(2, condition);
			pstat.setString(3, career);
			pstat.setString(4, intro);
			int result = pstat.executeUpdate();

			return result;

		}
	}


	// 인플루언서 회원 프로필 정보 가져오기
	public Profile_IfDTO selectBySeq(String paramSeq) throws Exception {

		String sql = "select * from profile_if where member_seq = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, paramSeq);
			try(ResultSet rs = pstat.executeQuery()){

				Profile_IfDTO dto = new Profile_IfDTO();
				if(rs.next()) {

					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setIntro_if(rs.getString("intro_if"));
					dto.setsLike_if(rs.getInt("slike_if"));
					dto.setrLike_if(rs.getInt("rlike_if"));
					return dto;
				}
				return null;
			}
		}
	}

	// 인플루언서 프로필 수정
	public int updateProfile(String seq, String condition, String career, String intro) throws Exception{

		String sql = "update profile_if set condition_if = ?, career_if = ?, intro_if = ? where member_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, condition);
			pstat.setString(2, career);
			pstat.setString(3, intro);
			pstat.setString(4, seq);

			int result = pstat.executeUpdate();
			return result;
		}
	}

	// 인플루언서 회원 탈퇴
	public int delete(String id) throws Exception{

		String sql = "delete from influencer where id_if = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	// 인플루언서 프로필 삭제
	public int deleteProfile(String id) throws Exception{

		String sql = "delete from profile_if where member_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	public int getIfCardCount(int seq) throws Exception { // 기업이 작성한 리뷰 수 출력.
		String sql = "select count(*) from review_cp where member_seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}

	public int getifCardPageTotalCount(int seq) throws Exception { // 인플루언서 페이지
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

	public String getifCardPageNavi(int currentPage,int seq) throws Exception { // 인플루언서 네비
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
			pageNavi +="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerProfile.ifcp?seq="+seq+"&cpage="+(startNavi-1)+"'>◀</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerProfile.ifcp?seq="+seq+"&cpage="+i+"'>"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "<li class='page-item'><a class='page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark' href='/influencerProfile.ifcp?seq="+seq+"&cpage="+(endNavi+1)+"'>▶</a></li>";
		}

		return pageNavi;
	}

	public List<Review_CpDTO> ifCardBoundary(int seq,int start, int end) throws Exception { // 9개씩 뽑아오는 코드.
		String sql = "select * from (select review_cp.*, row_number() over(order by seq desc) rn from review_cp where member_seq=?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			pstat.setInt(2, start);
			pstat.setInt(3, end);

			try(ResultSet rs = pstat.executeQuery();){
				List<Review_CpDTO> list = new ArrayList();
				while(rs.next()) {
					int seq1 = rs.getInt("seq");
					int member_seq = rs.getInt("member_seq");
					String writer = rs.getString("writer");
					String content = rs.getString("content");
					Timestamp timestamp = rs.getTimestamp("timestamp");
					int ref_seq = rs.getInt("ref_seq");

					Review_CpDTO dto = new Review_CpDTO(seq1,member_seq,writer,content,timestamp,ref_seq);

					list.add(dto);
				}
				return list;
			}
		}
	}

	public String whatIsLoggedInID(String loginID) throws Exception{
		String sql = "select * from influencer where id_if = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, loginID);
			try(ResultSet rs = pstat.executeQuery();){

				if(rs.next()) {

					return  "influencer";

				}
				return "company";
			}
		}
	}
	
	public String whatIsLoggedInIDforCompany(String loginID) throws Exception{
		String sql = "select * from company where id_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, loginID);
			try(ResultSet rs = pstat.executeQuery();){

				if(rs.next()) {

					return  "company";

				}
				return null;
			}
		}
	}


	// seq를 통해 인플루언서 사진의 부모seq추출.
	public String ifFindbySeq(String seq) throws Exception{
		String sql = "SELECT member_seq from profile_if WHERE seq_if = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, seq);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("member_seq");

				}
			}
			return result;
		}
	}

	// 인플루언서 사진의 부모시퀀스를 통해 sysname 추출.
	public String ifFindbyPseq(String pSeq) throws Exception{
		String sql = "SELECT sysname_influencer_file from file_influencer WHERE parentseq_influencer_file = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, pSeq);
			String result = "";
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getString("sysname_influencer_file");
				}
			}
			return result;
		}
	}


	// 깐부목록 불러오기
	public KkanbuDTO selectByKSeq(String paramSeq) throws Exception {

		String sql = "select * from kkanbu where influencer_seq = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, paramSeq);
			try(ResultSet rs = pstat.executeQuery()){

				KkanbuDTO dto = new KkanbuDTO();
				if(rs.next()) {					
					dto.setKkanbu_seq(rs.getInt("kkanbu_seq"));
					dto.setInfluencer_seq(rs.getInt("influencer_seq"));
					dto.setCompany_seq(rs.getInt("company_seq"));					
					return dto;
				}
				return null;
			}
		}
	}

	public int findIfSeq(int seq_if) throws Exception{ // 인플루언서 시퀀스로 인플루언서 프로필 시퀀스 찾기.
		String sql = "SELECT seq_if FROM profile_if WHERE member_seq =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq_if);
			int result = 0;
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					result = rs.getInt("seq_if");
				}
			}
			return result;
		}
	}

	public int insertReview(int seq,String loginID,String review,int seq_cp) throws Exception{ // 리뷰작성.
		String sql = "insert into review_cp values(review_cp_seq.nextval,?,?,?,sysdate,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			pstat.setString(2, loginID);
			pstat.setString(3, review);
			pstat.setInt(4, seq_cp);
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int cpDelete(String seq) throws Exception{ // 기업 제품등록 삭제.
		String sql = "delete from profile_if where seq_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat =con.prepareStatement(sql);){
			pstat.setString(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			return result;
		}
	}
}
