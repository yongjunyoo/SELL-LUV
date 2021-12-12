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

import kh.web.dto.CompanyDTO;
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
		String sql = "select count(*) from company";
		
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
	
	
	public ArrayList<CompanyDTO> selectByBound(int start, int end) throws Exception {
//		String sql ="select * from (select company.*, row_number() over(order by seq_cp desc) rn from company) where rn between ? and ?";
		String sql = "  SELECT * \n"
				+ "  from (select company.*, row_number() over(order by seq_cp desc) rn,decode(grade,'gold','A','silver','B','bronze','C') sort_grade from company ORDER BY sort_grade) \n"
				+ "   where rn between ? and ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql)){;
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery()){
			
			List<CompanyDTO> list = new ArrayList<>();
			
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
				String pwAsk = rs.getString("pwAsk");
				String pwAnswer = rs.getString("pwAnswer");
				
				CompanyDTO companyDTO = new CompanyDTO(seq,id,pw,photo,name,crnumber,zipcode,address1,address2,rpt,phone,email,sales,grade,pwAsk,pwAnswer);
				
				list.add(companyDTO);
			}
			return (ArrayList<CompanyDTO>) list;
			}
		}
	}
	
	//페이징 목록 출력 끝..
	//====================================================================================================================================

	
	// Company 회원가입 -이준협
	
	// 회원가입 method
	public int insert(String id, String pw, String photo, String name, String crunumber, String zipcode, String address1, 
			String address2, String rpt_cp, String phone, String email, String sales, String grade, String pwAsk, String pwAnswer ) throws Exception {
		
		String sql = "insert into company values(company_seq_cp.nextval,?,?,?,?,?,?,?,?,?,?,?,?,default,?,?)";
		
			
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			
			pstat.setString(1, id);
			pstat.setString(2, pw);			
			pstat.setString(3, photo);
			pstat.setString(4, name);
			pstat.setString(5, crunumber);
			pstat.setString(6, zipcode);
			pstat.setString(7, address1);
			pstat.setString(8, address2);
			pstat.setString(9, rpt_cp);
			pstat.setString(10, phone);
			pstat.setString(11, email);
			pstat.setString(12, sales);
			pstat.setString(13, pwAsk);
			pstat.setString(14, pwAnswer);
			int result  = pstat.executeUpdate();
			
			return result;
		}
	}
	
	// 회원가입 중복 ID 체크 method
	public boolean isIdExist(String id) throws Exception{

		String sql = "select * from company where id_cp = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,id);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}		
		}
	}
}
