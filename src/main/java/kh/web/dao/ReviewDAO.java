package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.Review_CpDTO;
import kh.web.dto.Review_IfDTO;
import kh.web.statics.IFCPStatics;

public class ReviewDAO {
	
	private static ReviewDAO instance = null;

	public static ReviewDAO getInstance() {
		if(instance==null) {
			instance = new ReviewDAO();
		}
		return instance;
	}
	public ReviewDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	// 인플루언서 리뷰 카운트
		public int getRecordCountReview() throws Exception{
			String sql = "select count(*) from review_if";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);
					ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
	
	// 기업 리뷰 카운트
		public int getRecordCountCPReview() throws Exception{
			String sql = "select count(*) from review_cp";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);
					ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
	
	// 인플루언서 게시글 개수파악
		public int getPageTotalCountReview() throws Exception{
			int recordTotalCount = this.getRecordCountReview(); 
			int pageTotalCount = 0; //
			if(recordTotalCount % IFCPStatics.RECORD_COUNT_PER_PAGE == 0) { 
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE;
			}else {
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE + 1;
			}
			return pageTotalCount;
		}
	
	// 기업 게시글 개수파악	
		public int getPageTotalCountCPReview() throws Exception{
			int recordTotalCount = this.getRecordCountCPReview(); 
			int pageTotalCount = 0; //
			if(recordTotalCount % IFCPStatics.RECORD_COUNT_PER_PAGE == 0) { 
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE;
			}else {
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE + 1;
			}
			return pageTotalCount;
		}
		
	// 인플루언서 페이징
		public String getPageNaviReview(int currentPage) throws Exception{

			// 총 몇개의 레코드(게시글)을 가지고 있는지
			int recordTotalCount = this.getRecordCountReview(); // 게시글 개수
			int recordCountPerPage = 9; // 한페이지에 출력되는 개수
			int naviCountPerPage = 3; // 밑에 페이지 숫자 출력

			int pageTotalCount = 0; //

			if(recordTotalCount % IFCPStatics.RECORD_COUNT_PER_PAGE == 0) { // 게시글 개수와 한페이 출력되는 개수를 나눴을 때 나머지가 없는 경우
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE;
			}else {// 게시글 개수와 한페이 출력되는 개수를 나눴을 때 나머지가 있는 경우 +1을해서 나머지 게시글도 출력하게 한다
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE + 1;
			}


			if(currentPage < 1) {
				currentPage = 1;  // 보완작업
			}else if(currentPage > pageTotalCount) {
				currentPage = pageTotalCount;
			} // currentPage 인자값을 클라이언트가 조작했을 시 방어하기 위한 코드

			int startNavi = (currentPage-1) / IFCPStatics.NAVI_PER_PAGE * IFCPStatics.NAVI_PER_PAGE +1;
			int endNavi = startNavi + IFCPStatics.NAVI_PER_PAGE -1 ;

			if(endNavi > pageTotalCount) {
				endNavi = pageTotalCount; // 보완작업
			}

			boolean needPrev = true;
			boolean needNext= true;

			if(startNavi == 1) {
				needPrev = false;
			}

			if(endNavi == pageTotalCount) {
				needNext = false;
			}

			String pageNavi = "";
			if(needPrev) {
				pageNavi += "<a href ='/IFReviewList.mem?cpage="+(startNavi-1)+"'><</a> ";		
			}

			for(int i = startNavi; i <= endNavi; i++) {
				pageNavi +="<a href='/IFReviewList.mem?cpage="+i+"'>" + i +"</a> ";
			}
			if(needNext) {
				pageNavi += "<a href ='/IFReviewList.mem?cpage="+(endNavi+1)+"'>></a> ";
			}

			return pageNavi;
		}
		
	// 기업 페이징
		public String getPageNaviCPReview(int currentPage) throws Exception{

			// 총 몇개의 레코드(게시글)을 가지고 있는지
			int recordTotalCount = this.getRecordCountCPReview(); // 게시글 개수
			int recordCountPerPage = 9; // 한페이지에 출력되는 개수
			int naviCountPerPage = 3; // 밑에 페이지 숫자 출력

			int pageTotalCount = 0; //

			if(recordTotalCount % IFCPStatics.RECORD_COUNT_PER_PAGE == 0) { // 게시글 개수와 한페이 출력되는 개수를 나눴을 때 나머지가 없는 경우
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE;
			}else {// 게시글 개수와 한페이 출력되는 개수를 나눴을 때 나머지가 있는 경우 +1을해서 나머지 게시글도 출력하게 한다
				pageTotalCount = recordTotalCount / IFCPStatics.RECORD_COUNT_PER_PAGE + 1;
			}


			if(currentPage < 1) {
				currentPage = 1;  // 보완작업
			}else if(currentPage > pageTotalCount) {
				currentPage = pageTotalCount;
			} // currentPage 인자값을 클라이언트가 조작했을 시 방어하기 위한 코드

			int startNavi = (currentPage-1) / IFCPStatics.NAVI_PER_PAGE * IFCPStatics.NAVI_PER_PAGE +1;
			int endNavi = startNavi + IFCPStatics.NAVI_PER_PAGE -1 ;

			if(endNavi > pageTotalCount) {
				endNavi = pageTotalCount; // 보완작업
			}

			boolean needPrev = true;
			boolean needNext= true;

			if(startNavi == 1) {
				needPrev = false;
			}

			if(endNavi == pageTotalCount) {
				needNext = false;
			}

			String pageNavi = "";
			if(needPrev) {
				pageNavi += "<a href ='/CPReviewList.mem?cpage="+(startNavi-1)+"'><</a> ";		
			}

			for(int i = startNavi; i <= endNavi; i++) {
				pageNavi +="<a href='/CPReviewList.mem?cpage="+i+"'>" + i +"</a> ";
			}
			if(needNext) {
				pageNavi += "<a href ='/CPReviewList.mem?cpage="+(endNavi+1)+"'>></a> ";
			}

			return pageNavi;
		}
		
		// 인플루언서 리뷰 모아보기
		public List<Review_IfDTO> selectByBoundReview(String seq, int start, int end) throws Exception{

			String sql = "select * from(select review_if.*, row_number() over(order by seq desc) rn from review_if where member_seq = ?) where rn between ? and ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, seq);
				pstat.setInt(2, start);
				pstat.setInt(3, end);
				try(ResultSet rs = pstat.executeQuery();){
					List<Review_IfDTO> list = new ArrayList<>();
					while(rs.next()) {
						Review_IfDTO dto = new Review_IfDTO();
						dto.setSeq(rs.getInt("seq"));
						dto.setMember_seq(rs.getInt("member_seq"));
						dto.setWriter(rs.getString("writer"));
						dto.setContent(rs.getString("content"));
						dto.setTimestamp(rs.getTimestamp("timestamp"));
						dto.setRef_seq(rs.getInt("ref_seq"));
						list.add(dto);
					}
					return list;
				}			
			}
		}
		
		//기업 리뷰 모아보기
		public List<Review_CpDTO> selectByBoundCPReview(String seq, int start, int end) throws Exception{

			String sql = "select * from(select review_cp.*, row_number() over(order by seq desc) rn from review_cp where member_seq = ?) where rn between ? and ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, seq);
				pstat.setInt(2, start);
				pstat.setInt(3, end);
				try(ResultSet rs = pstat.executeQuery();){
					List<Review_CpDTO> list = new ArrayList<>();
					while(rs.next()) {
						Review_CpDTO dto = new Review_CpDTO();
						dto.setSeq(rs.getInt("seq"));
						dto.setMember_seq(rs.getInt("member_seq"));
						dto.setWriter(rs.getString("writer"));
						dto.setContent(rs.getString("content"));
						dto.setTimestamp(rs.getTimestamp("timestamp"));
						dto.setRef_seq(rs.getInt("ref_seq"));
						list.add(dto);
					}
					return list;
				}			
			}
		}
		
		
}
