package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.BoardDTO;
import kh.web.dto.Board_CpDTO;
import kh.web.dto.CompanyDTO;
import kh.web.dto.InfluencerDTO;
import kh.web.dto.Profile_IfDTO;
import kh.web.statics.PageStatics;

public class AdminDAO {

	private static AdminDAO instance = null;
	public static AdminDAO getInstance() {
		if(instance==null) {
			instance = new AdminDAO();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int getIfCardCount() throws Exception { // 총 인플루언서 카드 수 출력.
		String sql = "select count(seq_if) from profile_if";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}
	public int getIfCardWriterCount(String searchContents) throws Exception { // 총 인플루언서 카드 수 출력.(소개글로 검색시)
		String sql = "select count(seq_if) from profile_if where intro_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	public int getIfCardConditionCount(String searchContents) throws Exception { // 총 인플루언서 카드 수 출력.(원하는 조건으로 검색시)
		String sql = "select count(seq_if) from profile_if where condition_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	public int getIfCardCareerCount(String searchContents) throws Exception { // 총 인플루언서 카드 수 출력.(커리어로 검색시)
		String sql = "select count(seq_if) from profile_if where career_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	
	public int getCpCardCount() throws Exception { // 총 기업 카드 수 출력.
		String sql = "select count(seq_board_cp) from board_cp";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}
	
	public int getCpCardTitleCount(String searchContents) throws Exception { // 총 기업 카드 수 출력.(제목으로 검색시)
		String sql = "select count(seq_board_cp) from board_cp where title_cp like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	
	public int getCpCardWriterCount(String searchContents) throws Exception { // 총 기업 카드 수 출력.(원하는 조건으로 검색시)
		String sql = "select count(seq_board_cp) from board_cp where condition_cp like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	public int getCpCardIntroCount(String searchContents) throws Exception { // 총 기업 카드 수 출력.(소개글로 검색시)
		String sql = "select count(seq_board_cp) from board_cp where intro_cp like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}

	public int getAllBoardCount() throws Exception { // 메인에서 총 자유게시판 글 수 출력.
		String sql = "select count(*) from freeboard";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}
	public int getAllBoardWriterCount(String searchContents) throws Exception { // 메인에서 총 자유게시판 글 수 출력.(작성자로 검색시)
		String sql = "select count(*) from freeboard where writer like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);	
			}	
		}
	}
	public int getAllBoardTitleCount(String searchContents) throws Exception { // 메인에서 총 자유게시판 글 수 출력.(제목으로 검색시)
		String sql = "select count(*) from freeboard where title like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}

	public int getIfMemberCount() throws Exception { // 메인에서 인플루언서 회원 수 출력.
		String sql = "select count(*) from influencer";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}
	public int getIfMemberIdCount(String searchContents) throws Exception { // 메인에서 인플루언서 회원 수 출력.(아이디로 검색시)
		String sql = "select count(*) from influencer where id_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}
	public int getIfMemberNameCount(String searchContents) throws Exception { // 메인에서 인플루언서 회원 수 출력.(이름으로 검색시)
		String sql = "select count(*) from influencer where name_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}
	public int getIfMemberNNCount(String searchContents) throws Exception { // 메인에서 인플루언서 회원 수 출력.(닉네임으로 검색시)
		String sql = "select count(*) from influencer where nickname_if like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}
	
	public int getCpMemberCount() throws Exception { // 메인에서 기업 회원 수 출력.
		String sql = "select count(*) from company";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}
	
	public int getCpMemberIdCount(String searchContents) throws Exception { // 메인에서 기업 회원 수 출력.(아이디로 찾을시)
		String sql = "select count(*) from company where id_cp like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}
	
	public int getCpMemberNameCount(String searchContents) throws Exception { // 메인에서 기업 회원 수 출력.(기업이름으로 찾을시)
		String sql = "select count(*) from company where name_cp like ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);	
			}
		}
	}
	
	public int getAllMemberCount() throws Exception { // 메인에서 총 회원 수 출력.
		String sql = "select (select count(seq_cp) from company)+(select count(seq_if) from influencer) from dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);	
		}
	}

	public int[] getGradeCount() throws Exception { // 메인에서 등급 별 회원 출력.
		String sql = "select (select count(grade) from company where grade='gold')+(select count(grade) from influencer where grade='gold') goldcount, (select count(grade) from company where grade='silver')+(select count(grade) from influencer where grade='silver') silvercount, (select count(grade) from company where grade='bronze')+(select count(grade) from influencer where grade='bronze') bronzecount from dual";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			int[] list = new int[3];
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					int gold = rs.getInt(1);
					int silver = rs.getInt(2);
					int bronze = rs.getInt(3);
					list[0]=gold;
					list[1]=silver;
					list[2]=bronze;
				}
				return list;
			}
		}
	}
	
	public int getifCardPageTotalCount() throws Exception { // 인플루언서 카드 페이지
		int recordTotalCount = this.getIfCardCount();
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getifCardWriterPageTotalCount(String searchContents) throws Exception { // 인플루언서 카드 페이지(소개글로 검색시)
		int recordTotalCount = this.getIfCardWriterCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getifCardConditionPageTotalCount(String searchContents) throws Exception { // 인플루언서 카드 페이지(원하는조건으로 검색시)
		int recordTotalCount = this.getIfCardConditionCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getifCardCareerPageTotalCount(String searchContents) throws Exception { // 인플루언서 카드 페이지(커리어로 검색시)
		int recordTotalCount = this.getIfCardCareerCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public String getifCardPageNavi(int currentPage) throws Exception { // 인플루언서 카드 네비
		int recordTotalCount = this.getIfCardCount();

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCard.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfCard.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCard.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		
		return pageNavi;
	}
	
	public String getifCardWriterPageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 카드 네비(소개글로 검색시)
		int recordTotalCount = this.getIfCardWriterCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=소개글&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfCardSearch.admin?select=소개글&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=소개글&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		
		return pageNavi;
	}
	public String getifCardConditionPageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 카드 네비(원하는 조건으로 검색시)
		int recordTotalCount = this.getIfCardConditionCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=원하는조건&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfCardSearch.admin?select=원하는조건&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=원하는조건&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		
		return pageNavi;
	}
	public String getifCardCareerPageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 카드 네비(커리어로 검색시)
		int recordTotalCount = this.getIfCardCareerCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=커리어&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfCardSearch.admin?select=커리어&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfCardSearch.admin?select=커리어&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		
		return pageNavi;
	}
	
	public List<Profile_IfDTO> ifCardBoundary(int start, int end) throws Exception { // 인플루언서 10개씩 뽑아오는 코드.
		String sql = "select * from (select profile_if.*, row_number() over(order by seq_if desc) rn from profile_if) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Profile_IfDTO> list = new ArrayList();
				while(rs.next()) {
					Profile_IfDTO dto = new Profile_IfDTO();
					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setIntro_if(rs.getString("intro_if"));
					dto.setsLike_if(rs.getInt("sLike_if"));
					dto.setrLike_if(rs.getInt("rLike_if"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int getCpCardPageTotalCount() throws Exception { // 기업 카드 페이지
		int recordTotalCount = this.getCpCardCount();
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getCpCardWriterPageTotalCount(String searchContents) throws Exception { // 기업 카드 페이지(원하는 조건으로 검색시)
		int recordTotalCount = this.getCpCardWriterCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getCpCardIntroPageTotalCount(String searchContents) throws Exception { // 기업 카드 페이지(소개글로 검색시)
		int recordTotalCount = this.getCpCardIntroCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getCpCardTitlePageTotalCount(String searchContents) throws Exception { // 기업 카드 페이지(제목으로 검색시)
		int recordTotalCount = this.getCpCardTitleCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public String getCpCardPageNavi(int currentPage) throws Exception { // 기업 카드 네비
		int recordTotalCount = this.getCpCardCount();

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCard.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpCard.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCard.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public String getCpCardTitlePageNavi(int currentPage,String searchContents) throws Exception { // 기업 카드 네비(제목으로 검색시)
		int recordTotalCount = this.getCpCardTitleCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=제목&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpCardSearch.admin?select=제목&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=제목&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public String getCpCardWriterPageNavi(int currentPage, String searchContents) throws Exception { // 기업 카드 네비(원하는 조건으로 검색시)
		int recordTotalCount = this.getCpCardWriterCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=원하는조건&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpCardSearch.admin?select=원하는조건&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=원하는조건&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getCpCardIntroPageNavi(int currentPage, String searchContents) throws Exception { // 기업 카드 네비(소개글로 검색시)
		int recordTotalCount = this.getCpCardIntroCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=소개글&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpCardSearch.admin?select=소개글&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpCardSearch.admin?select=소개글&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public List<Board_CpDTO> cpCardBoundary(int start, int end) throws Exception { // 기업 10개씩 뽑아오는 코드.
		String sql = "select * from (select board_cp.*, row_number() over(order by seq_board_cp desc) rn from board_cp) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Board_CpDTO> list = new ArrayList();
				while(rs.next()) {
					Board_CpDTO dto = new Board_CpDTO();
					dto.setSeq_cp(rs.getInt("seq_board_cp"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setTitle_cp(rs.getString("title_cp"));
					dto.setCondition_cp(rs.getString("condition_cp"));
					dto.setIntro_cp(rs.getString("intro_cp"));
					dto.setsLike_cp(rs.getInt("sLike_cp"));
					dto.setrLike_cp(rs.getInt("rLike_cp"));
					dto.setPhoto_cp(rs.getString("photo_cp"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int getBoardPageTotalCount() throws Exception { // 자유게시판 페이지
		int recordTotalCount = this.getAllBoardCount();
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public int getBoardTitlePageTotalCount(String searchContents) throws Exception { // 자유게시판 페이지(제목)
		int recordTotalCount = this.getAllBoardTitleCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public int getBoardWriterPageTotalCount(String searchContents) throws Exception { // 자유게시판 페이지(작성자)
		int recordTotalCount = this.getAllBoardWriterCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public String getBoardPageNavi(int currentPage) throws Exception { // 자유게시판 네비
		int recordTotalCount = this.getAllBoardCount();

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoard.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminBoard.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoard.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public String getBoardTitlePageNavi(int currentPage,String searchContents) throws Exception { // 자유게시판 네비(제목으로 검색)
		int recordTotalCount = this.getAllBoardTitleCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoardSearch.admin?select=제목&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminBoardSearch.admin?select=제목&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoardSearch.admin?select=제목&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public String getBoardWriterPageNavi(int currentPage,String searchContents) throws Exception { // 자유게시판 네비(작성자로 검색)
		int recordTotalCount = this.getAllBoardWriterCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoardSearch.admin?select=작성자&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminBoardSearch.admin?select=작성자&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminBoardSearch.admin?select=작성자&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public List<BoardDTO> boardBoundary(int start, int end) throws Exception { // 자유게시판 10개씩 뽑아오는 코드.
		String sql = "select * from (select freeboard.*, row_number() over(order by seq desc) rn from freeboard) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setWriter(rs.getString("writer"));
					dto.setTitle(rs.getString("title"));
					dto.setContents(rs.getString("contents"));
					dto.setWrite_date(rs.getTimestamp("write_date"));
					dto.setView_count(rs.getInt("view_count"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int getCpMemberPageTotalCount() throws Exception { // 기업회원 페이지
		int recordTotalCount = this.getCpMemberCount();
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getCpMemberIdPageTotalCount(String searchContents) throws Exception { // 기업회원 페이지(아이디로 검색시)
		int recordTotalCount = this.getCpMemberIdCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getCpMemberNamePageTotalCount(String searchContents) throws Exception { // 기업회원 페이지(기업이름으로 검색시)
		int recordTotalCount = this.getCpMemberNameCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public String getCpMemberPageNavi(int currentPage) throws Exception { // 기업회원 네비
		int recordTotalCount = this.getCpMemberCount();

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMember.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpMember.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMember.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getCpMemberIdPageNavi(int currentPage,String searchContents) throws Exception { // 기업회원 네비(아이디로 검색시)
		int recordTotalCount = this.getCpMemberIdCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMemberSearch.admin?select=아이디&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpMemberSearch.admin?select=아이디&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMemberSearch.admin?select=아이디&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getCpMemberNamePageNavi(int currentPage,String searchContents) throws Exception { // 기업회원 네비(기업이름으로 검색시)
		int recordTotalCount = this.getCpMemberNameCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMember.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminCpMember.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminCpMember.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public List<CompanyDTO> cpMemberBoundary(int start, int end) throws Exception { // 기업회원 10개씩 뽑아오는 코드.
		String sql = "select * from (select company.*, row_number() over(order by seq_cp desc) rn from company) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<CompanyDTO> list = new ArrayList();
				while(rs.next()) {
					CompanyDTO dto = new CompanyDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int getIfMemberPageTotalCount() throws Exception { // 인플루언서 회원 페이지
		int recordTotalCount = this.getIfMemberCount();
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getIfMemberIdPageTotalCount(String searchContents) throws Exception { // 인플루언서 회원 페이지(아이디로 검색시)
		int recordTotalCount = this.getIfMemberIdCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getIfMemberNamePageTotalCount(String searchContents) throws Exception { // 인플루언서 회원 페이지(이름으로 검색시)
		int recordTotalCount = this.getIfMemberNameCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	public int getIfMemberNNPageTotalCount(String searchContents) throws Exception { // 인플루언서 회원 페이지(닉네임으로 검색시)
		int recordTotalCount = this.getIfMemberNNCount(searchContents);
		
		// 총 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}
		return pageTotalCount;
	}
	
	public String getIfMemberPageNavi(int currentPage) throws Exception { // 인플루언서 회원 네비
		int recordTotalCount = this.getIfMemberCount();

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMember.admin?cpage="+(startNavi-1)+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfMember.admin?cpage="+i+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMember.admin?cpage="+(endNavi+1)+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getIfMemberIdPageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 회원 네비(아이디로 검색시)
		int recordTotalCount = this.getIfMemberIdCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=아이디&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfMemberSearch.admin?select=아이디&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=아이디&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getIfMemberNamePageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 회원 네비(이름으로 검색시)
		int recordTotalCount = this.getIfMemberNameCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=이름&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfMemberSearch.admin?select=이름&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=이름&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	public String getIfMemberNNPageNavi(int currentPage,String searchContents) throws Exception { // 인플루언서 회원 네비(닉네임으로 검색시)
		int recordTotalCount = this.getIfMemberNNCount(searchContents);

		int pageTotalCount = 0;
		if(recordTotalCount%PageStatics.RECORD_COUNT_PER_PAGE==0) {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE;
		}else {
			pageTotalCount = recordTotalCount/PageStatics.RECORD_COUNT_PER_PAGE+1;
		}

		int startNavi = (currentPage-1)/PageStatics.NAVI_COUNT_PER_PAGE*PageStatics.NAVI_COUNT_PER_PAGE+1;
		int endNavi = startNavi+PageStatics.NAVI_COUNT_PER_PAGE-1;
		
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
			pageNavi +="								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=닉네임&cpage="+(startNavi-1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Previous</span>\r\n"
					+ "								</a></li>";
		}
		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi+="<li class=\"page-item\"><a class=\"page-link\" href=/adminIfMemberSearch.admin?select=닉네임&cpage="+i+"&searchContents="+searchContents+">"+i+"</a></li>";
		}
		if(needNext) {
			pageNavi += "								<li class=\"page-item\"><a class=\"page-link\" href=\"/adminIfMemberSearch.admin?select=닉네임&cpage="+(endNavi+1)+"&searchContents="+searchContents+"\"\r\n"
					+ "									aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "										<span class=\"sr-only\">Next</span>\r\n"
					+ "								</a></li>";
		}
		return pageNavi;
	}
	
	public List<InfluencerDTO> ifMemberBoundary(int start, int end) throws Exception { // 인플루언서 회원 10개씩 뽑아오는 코드.
		String sql = "select * from (select influencer.*, row_number() over(order by seq_if desc) rn from influencer) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<InfluencerDTO> list = new ArrayList();
				while(rs.next()) {
					InfluencerDTO dto = new InfluencerDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}

	
	public List<BoardDTO> boardSearchByTitle(String searchContents, int start, int end) throws Exception { // 자유게시판 제목으로 검색.
		String sql = "select * from (select freeboard.*, row_number() over(order by seq desc) rn from freeboard where title like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, "%"+searchContents+"%");
				pstat.setInt(2, start);
				pstat.setInt(3, end);
				List<BoardDTO> list = new ArrayList();
			try(ResultSet rs = pstat.executeQuery();){
				while(rs.next()) {
					int seq = rs.getInt(1);
					String writer = rs.getString(2);
					String title = rs.getString(3);
					String contents = rs.getString(4);
					Timestamp write_date = rs.getTimestamp(5);
					int view_count = rs.getInt(6);
					BoardDTO dto = new BoardDTO(seq,writer,title,contents,write_date,view_count,null);
					list.add(dto);
				}
			}
			return list;
		}
	}
	
	public List<BoardDTO> boardSearchByWriter(String searchContents, int start, int end) throws Exception { // 자유게시판 작성자로 검색.
		String sql = "select * from (select freeboard.*, row_number() over(order by seq desc) rn from freeboard where writer like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, "%"+searchContents+"%");
				pstat.setInt(2, start);
				pstat.setInt(3, end);
				List<BoardDTO> list = new ArrayList();
			try(ResultSet rs = pstat.executeQuery();){
				while(rs.next()) {
					int seq = rs.getInt(1);
					String writer = rs.getString(2);
					String title = rs.getString(3);
					String contents = rs.getString(4);
					Timestamp write_date = rs.getTimestamp(5);
					int view_count = rs.getInt(6);
					BoardDTO dto = new BoardDTO(seq,writer,title,contents,write_date,view_count,null);
					list.add(dto);
				}
			}
			return list;
		}
	}
	
	public int deleteBoard(int seq) throws Exception { // 자유게시판 글 삭제
		String sql = "delete from freeboard where seq=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public List<Board_CpDTO> cpCardSearchByTitle(String searchContents, int start, int end) throws Exception { // 기업카드 제목으로 검색
		String sql = "select * from (select board_cp.*, row_number() over(order by seq_board_cp desc) rn from board_cp where title_cp like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Board_CpDTO> list = new ArrayList();
				while(rs.next()) {
					Board_CpDTO dto = new Board_CpDTO();
					dto.setSeq_cp(rs.getInt("seq_board_cp"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setTitle_cp(rs.getString("title_cp"));
					dto.setCondition_cp(rs.getString("condition_cp"));
					dto.setIntro_cp(rs.getString("intro_cp"));
					dto.setsLike_cp(rs.getInt("sLike_cp"));
					dto.setrLike_cp(rs.getInt("rLike_cp"));
					dto.setPhoto_cp(rs.getString("photo_cp"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<Board_CpDTO> cpCardSearchByWriter(String searchContents, int start, int end) throws Exception { // 기업카드 원하는 조건으로 검색
		String sql = "select * from (select board_cp.*, row_number() over(order by seq_board_cp desc) rn from board_cp where condition_cp like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Board_CpDTO> list = new ArrayList();
				while(rs.next()) {
					Board_CpDTO dto = new Board_CpDTO();
					dto.setSeq_cp(rs.getInt("seq_board_cp"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setTitle_cp(rs.getString("title_cp"));
					dto.setCondition_cp(rs.getString("condition_cp"));
					dto.setIntro_cp(rs.getString("intro_cp"));
					dto.setsLike_cp(rs.getInt("sLike_cp"));
					dto.setrLike_cp(rs.getInt("rLike_cp"));
					dto.setPhoto_cp(rs.getString("photo_cp"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	public List<Board_CpDTO> cpCardSearchByIntro(String searchContents, int start, int end) throws Exception { // 기업카드 소개글로 검색
		String sql = "select * from (select board_cp.*, row_number() over(order by seq_board_cp desc) rn from board_cp where intro_cp like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Board_CpDTO> list = new ArrayList();
				while(rs.next()) {
					Board_CpDTO dto = new Board_CpDTO();
					dto.setSeq_cp(rs.getInt("seq_board_cp"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setTitle_cp(rs.getString("title_cp"));
					dto.setCondition_cp(rs.getString("condition_cp"));
					dto.setIntro_cp(rs.getString("intro_cp"));
					dto.setsLike_cp(rs.getInt("sLike_cp"));
					dto.setrLike_cp(rs.getInt("rLike_cp"));
					dto.setPhoto_cp(rs.getString("photo_cp"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int deleteCpCard(int seq) throws Exception { // 기업카드 글 삭제
		String sql = "delete from board_cp where seq_board_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public List<CompanyDTO> cpMemberSearchById(String searchContents,int start, int end) throws Exception { // 기업회원 아이디로 검색
		String sql = "select * from (select company.*, row_number() over(order by seq_cp desc) rn from company where id_cp like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<CompanyDTO> list = new ArrayList();
				while(rs.next()) {
					CompanyDTO dto = new CompanyDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<CompanyDTO> cpMemberSearchByName(String searchContents,int start, int end) throws Exception { // 기업회원 기업이름으로 검색
		String sql = "select * from (select company.*, row_number() over(order by seq_cp desc) rn from company where name_cp like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<CompanyDTO> list = new ArrayList();
				while(rs.next()) {
					CompanyDTO dto = new CompanyDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int deleteCpMember(int seq) throws Exception { // 기업회원 글 삭제
		String sql = "delete from company where seq_cp=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public List<Profile_IfDTO> ifCardSearchByWriter(String searchContents,int start, int end) throws Exception { // 인플루언서 카드 소개글로 검색
		String sql = "select * from (select profile_if.*, row_number() over(order by seq_if desc) rn from profile_if where intro_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Profile_IfDTO> list = new ArrayList();
				while(rs.next()) {
					Profile_IfDTO dto = new Profile_IfDTO();
					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setIntro_if(rs.getString("intro_if"));
					dto.setsLike_if(rs.getInt("sLike_if"));
					dto.setrLike_if(rs.getInt("rLike_if"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	public List<Profile_IfDTO> ifCardSearchByCondition(String searchContents,int start, int end) throws Exception { // 인플루언서 카드 원하는조건으로 검색
		String sql = "select * from (select profile_if.*, row_number() over(order by seq_if desc) rn from profile_if where condition_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Profile_IfDTO> list = new ArrayList();
				while(rs.next()) {
					Profile_IfDTO dto = new Profile_IfDTO();
					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setIntro_if(rs.getString("intro_if"));
					dto.setsLike_if(rs.getInt("sLike_if"));
					dto.setrLike_if(rs.getInt("rLike_if"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	public List<Profile_IfDTO> ifCardSearchByCareer(String searchContents,int start, int end) throws Exception { // 인플루언서 카드 커리어로 검색
		String sql = "select * from (select profile_if.*, row_number() over(order by seq_if desc) rn from profile_if where career_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<Profile_IfDTO> list = new ArrayList();
				while(rs.next()) {
					Profile_IfDTO dto = new Profile_IfDTO();
					dto.setSeq_if(rs.getInt("seq_if"));
					dto.setMember_seq(rs.getInt("member_seq"));
					dto.setCondition_if(rs.getString("condition_if"));
					dto.setCareer_if(rs.getString("career_if"));
					dto.setIntro_if(rs.getString("intro_if"));
					dto.setsLike_if(rs.getInt("sLike_if"));
					dto.setrLike_if(rs.getInt("rLike_if"));
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int deleteIfCard(int seq) throws Exception { // 인플루언서 카드 글 삭제
		String sql = "delete from profile_if where seq_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
	
	public List<InfluencerDTO> ifMemberSearchById(String searchContents,int start, int end) throws Exception { // 인플루언서 회원 아이디로 검색
		String sql = "select * from (select influencer.*, row_number() over(order by seq_if desc) rn from influencer where id_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<InfluencerDTO> list = new ArrayList();
				while(rs.next()) {
					InfluencerDTO dto = new InfluencerDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<InfluencerDTO> ifMemberSearchByName(String searchContents,int start, int end) throws Exception { // 인플루언서 회원 이름으로 검색
		String sql = "select * from (select influencer.*, row_number() over(order by seq_if desc) rn from influencer where name_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<InfluencerDTO> list = new ArrayList();
				while(rs.next()) {
					InfluencerDTO dto = new InfluencerDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<InfluencerDTO> ifMemberSearchByNickname(String searchContents,int start, int end) throws Exception { // 인플루언서 회원 닉네임으로 검색
		String sql = "select * from (select influencer.*, row_number() over(order by seq_if desc) rn from influencer where nickname_if like ?) where rn between ? and ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+searchContents+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			
			try(ResultSet rs = pstat.executeQuery();){
				List<InfluencerDTO> list = new ArrayList();
				while(rs.next()) {
					InfluencerDTO dto = new InfluencerDTO();
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
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public int deleteIfMember(int seq) throws Exception { // 인플루언서 회원 글 삭제
		String sql = "delete from influencer where seq_if=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			return result;
		}
	}
}