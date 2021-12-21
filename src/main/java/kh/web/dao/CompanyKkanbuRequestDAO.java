package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.CompanyKkanbuRequestDTO;
import kh.web.dto.InfluencerKkanbuRequestDTO;

public class CompanyKkanbuRequestDAO {

	
	private static CompanyKkanbuRequestDAO instance = null;

	public static CompanyKkanbuRequestDAO getInstance() {
		if(instance==null) {
			instance = new CompanyKkanbuRequestDAO();
		}
		return instance;
	}
	public CompanyKkanbuRequestDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	public boolean isRequestStillPending(int kkanbuSeqFrom, int kkanbuSeqTo) throws SQLException, Exception {
		String sql = "SELECT * FROM companyKkanbuRequest WHERE cp_kkanbuSeqFrom=? and cp_kkanbuSeqTo =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeqFrom);
			pstat.setInt(2,  kkanbuSeqTo);
			try(ResultSet rs = pstat.executeQuery();){
				boolean result = false;
				if(rs.next()) {
					result = true;
				
				}
				return result;
			}
			
			}
	
	}
	public int sendKkanbuRequest(int kkanbuSeqFrom, int kkanbuSeqTo, String kkanbuNameFrom, String kkanbuNickNameTo, int cp_KkanbuSeq ,String cp_title_cp) throws SQLException, Exception {
		String sql = "INSERT INTO companyKkanbuRequest VALUES (cp_kkanbu_seq.nextval,?,?,?,?,DEFAULT,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeqFrom);
			pstat.setInt(2, kkanbuSeqTo);
			pstat.setString(3, kkanbuNameFrom);
			pstat.setString(4, kkanbuNickNameTo);
			pstat.setInt(5, cp_KkanbuSeq);
			pstat.setString(6, kkanbuNickNameTo);
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	public List<CompanyKkanbuRequestDTO> getKkanbuRequest(int loggedInSeq) throws SQLException, Exception {
		String sql = "SELECT * FROM companyKkanbuRequest WHERE cp_kkanbuSeqTo=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, loggedInSeq);
			try(ResultSet rs = pstat.executeQuery();){
				
				List<CompanyKkanbuRequestDTO> list = new ArrayList<CompanyKkanbuRequestDTO>();
				
				while(rs.next()) {
					int seq = rs.getInt("cp_kkanbu_Seq");
					int cp_kkanbuSeqFrom = rs.getInt("cp_kkanbuSeqFrom");
					int cp_kkanbuSeqTo = rs.getInt("cp_kkanbuSeqTo");
					String cp_kkanbuNameFrom = rs.getString("cp_kkanbuNameFrom");
					String  cp_kkanbuNickNameTo = rs.getString("cp_kkanbuNickNameTo");
					Timestamp requestedTime = rs.getTimestamp("cp_requestedTime");
					int cp_kkanbuCardSeq = rs.getInt("cp_kkanbuCardSeq");
		              String cp_title_cp = rs.getString("cp_title_cp");
					CompanyKkanbuRequestDTO companyKkanbuRequestDTO  = new CompanyKkanbuRequestDTO(seq,cp_kkanbuSeqFrom,cp_kkanbuSeqTo,cp_kkanbuNameFrom,cp_kkanbuNickNameTo,requestedTime,cp_kkanbuCardSeq,cp_title_cp);
				
					list.add(companyKkanbuRequestDTO);
				}
				return list;
			}
			
			}
	}
	public int delete(int kkanbuSeq) throws SQLException, Exception {
		String sql = "DELETE FROM companyKkanbuRequest WHERE cp_kkanbu_seq=?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeq);
			int result = pstat.executeUpdate();
			return result;
		}
	}

}
