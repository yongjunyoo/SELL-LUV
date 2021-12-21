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

import kh.web.dto.KkanbuDTO;

public class KkanbuDAO {

	private static KkanbuDAO instance = null;

	public static KkanbuDAO getInstance() {
		if(instance==null) {
			instance = new KkanbuDAO();
		}
		return instance;
	}
	public KkanbuDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	public int insertKkanbu(int companySeq, int influencerSeq, int kkanbuCardSeq, String cp_title_cp) throws SQLException, Exception {
		String sql = "insert into kkanbu values(kkanbu_seq.nextval,?,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, influencerSeq);
			pstat.setInt(2, companySeq);
			pstat.setInt(3, kkanbuCardSeq);
			pstat.setString(4, cp_title_cp);
			
			int result = pstat.executeUpdate();
			
			return result;
		}
	}
	public ArrayList<KkanbuDTO> getInfKkanbu(int loggedInSeq) throws SQLException, Exception {
		
		String sql = "select * from kkanbu where influencer_Seq = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, loggedInSeq);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<KkanbuDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					int kkanbu_seq = rs.getInt("kkanbu_seq");
					int influencer_seq = rs.getInt("influencer_seq");
					int company_seq = rs.getInt("company_seq");
					
					KkanbuDTO kkanbuDTO = new KkanbuDTO(kkanbu_seq,influencer_seq,company_seq);
					list.add(kkanbuDTO);
				}
				return list;
					}
				}
			}
	
public ArrayList<KkanbuDTO> getCompanyKkanbu(int loggedInSeq) throws SQLException, Exception {
		
		String sql = "select * from kkanbu where company_Seq = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, loggedInSeq);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<KkanbuDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					int kkanbu_seq = rs.getInt("kkanbu_seq");
					int influencer_seq = rs.getInt("influencer_seq");
					int company_seq = rs.getInt("company_seq");
					
					KkanbuDTO kkanbuDTO = new KkanbuDTO(kkanbu_seq,influencer_seq,company_seq);
					list.add(kkanbuDTO);
				}
				return list;
					}
				}
			}
public boolean areTheyKkanbu(int kkanbuSeqFrom, int kkanbuCardSeq) throws SQLException, Exception {
	String sql = "select * from kkanbu where influencer_seq =? and kkanbuCardSeq=?";
	
	try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
		pstat.setInt(1, kkanbuSeqFrom);
		pstat.setInt(2, kkanbuCardSeq);
		try(ResultSet rs = pstat.executeQuery();){
			
				boolean result = rs.next();
				
				return result;
		
				}
		}
}
public boolean CompanyareTheyKkanbu(int kkanbuSeqFrom, int kkanbuSeqTo) throws SQLException, Exception {
	String sql = "select * from kkanbu where company_Seq = ? and influencer_seq =?";
	
	try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
		pstat.setInt(1,kkanbuSeqTo );
		pstat.setInt(2,kkanbuSeqFrom );
		try(ResultSet rs = pstat.executeQuery();){
			
				boolean result = rs.next();
				
				return result;
		
				}
		}
}
public int getKkanbuSeq(int kkanbuSeqFrom, int kkanbuSeqTo) throws SQLException, Exception {
String sql = "select kkanbu_seq from kkanbu where company_Seq = ? and influencer_seq =?";
	
	try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
		pstat.setInt(1, kkanbuSeqFrom);
		pstat.setInt(2, kkanbuSeqTo);
		try(ResultSet rs = pstat.executeQuery();){
			int kkanbu_seq = 0;
				if(rs.next()) {
					 kkanbu_seq = rs.getInt("kkanbu_seq");
					
				}
				return kkanbu_seq;
				}
		}
	}

	public int getMemberSeq(String title_cp) throws Exception{
		   
	    String sql = "select member_seq from board_cp where title_cp = ?";
	
	    try(Connection con = this.getConnection();
	          PreparedStatement pstat = con.prepareStatement(sql);){
	       pstat.setString(1, title_cp);
	       try(ResultSet rs = pstat.executeQuery();){
	    	   int member_seq = 0;
	          if(rs.next()) {
	              member_seq = rs.getInt("member_seq");
	             
	          }
	          return member_seq;
	       }
	
	    }
	}
	
	public int getBoardCpSeq(String title_cp) throws Exception{
		   
	    String sql = "select seq_board_cp from board_cp where title_cp LIKE ?";
	
	    try(Connection con = this.getConnection();
	          PreparedStatement pstat = con.prepareStatement(sql);){
	       pstat.setString(1, "%"+title_cp+"%");
	       try(ResultSet rs = pstat.executeQuery();){
	    	   int member_seq = 0;
	          if(rs.next()) {
	              member_seq = rs.getInt("seq_board_cp");
	             
	          }
	          return member_seq;
	       }
	
	    }
	}
	
	public List<String> selectByBSeq(String seq) throws Exception{
		   
	       String sql = "select title_cp from board_cp where member_seq = ?";
	   
	       try(Connection con = this.getConnection();
	             PreparedStatement pstat = con.prepareStatement(sql);){
	          pstat.setString(1, seq);
	          try(ResultSet rs = pstat.executeQuery();){
	             List<String> list = new ArrayList<>();
	             while(rs.next()) {
	                String title = rs.getString("title_cp");
	                list.add(title);
	             }
	             return list;
	          }
	   
	       }
	    }
	
}