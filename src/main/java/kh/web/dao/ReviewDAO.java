package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	// 인플루언서 마이페이지 리뷰 모아보기
	
		public int getRecordCountReview() throws Exception{
			String sql = "select count(*) from review_if";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);
					ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
		
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
				pageNavi += "<a href ='/boardlist.board?cpage="+(startNavi-1)+"'><</a> ";		
			}

			for(int i = startNavi; i <= endNavi; i++) {
				pageNavi +="<a href='/boardlist.board?cpage="+i+"'>" + i +"</a> ";
			}
			if(needNext) {
				pageNavi += "<a href ='/boardlist.board?cpage="+(endNavi+1)+"'>></a> ";
			}

			return pageNavi;
		}
		
		public List<BoardDTO> selectByBoundReview(int start, int end) throws Exception{

			String sql = "select * from(select review_if.*, row_number() over(order by seq desc) rn from review_if) where rn between ? and ?";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setInt(1, start);
				pstat.setInt(2, end);
				try(ResultSet rs = pstat.executeQuery();){
					List<BoardDTO> list = new ArrayList<>();
					while(rs.next()) {
						BoardDTO dto = new BoardDTO();
						dto.setSeq(rs.getInt("seq"));
						dto.setWriter(rs.getString("writer"));
						dto.setTitle(rs.getString("title"));
						dto.setContents(rs.getString("title"));				
						dto.setWrite_date(rs.getDate("write_date"));
						dto.setView_count(rs.getInt("view_count"));
						list.add(dto);
					}
					return list;
				}			
			}
		}
}
