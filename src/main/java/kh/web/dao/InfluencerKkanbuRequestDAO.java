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

import kh.web.dto.BoardDTO;
import kh.web.dto.InfluencerKkanbuRequestDTO;

public class InfluencerKkanbuRequestDAO {
	
	private static InfluencerKkanbuRequestDAO instance = null;

	public static InfluencerKkanbuRequestDAO getInstance() {
		if(instance==null) {
			instance = new InfluencerKkanbuRequestDAO();
		}
		return instance;
	}
	public InfluencerKkanbuRequestDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	public int sendKkanbuRequest(int kkanbuSeqFrom,int kkanbuSeqTo, String kkanbuNameFrom, String kkanbuNickNameTo, int kkanbuCardSeq, String title_cp ) throws SQLException, Exception {
		
			String sql = "INSERT INTO InfluencerKkanbuRequest VALUES (if_kkanbu_seq.nextval,?,?,?,?,DEFAULT,?,?)";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setInt(1, kkanbuSeqFrom);
				pstat.setInt(2, kkanbuSeqTo);
				pstat.setString(3, kkanbuNameFrom);
				pstat.setString(4, kkanbuNickNameTo);
				pstat.setInt(5, kkanbuCardSeq);
				pstat.setString(6, title_cp);
				int result = pstat.executeUpdate();
				return result;
			}
		}
	
	public List<InfluencerKkanbuRequestDTO> getKkanbuRequest(int loggedInSeq) throws SQLException, Exception {
		String sql = "SELECT * FROM influencerKkanbuRequest WHERE if_kkanbuSeqTo=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, loggedInSeq);
			try(ResultSet rs = pstat.executeQuery();){
				
				List<InfluencerKkanbuRequestDTO> list = new ArrayList<InfluencerKkanbuRequestDTO>();
				
				while(rs.next()) {
					int seq = rs.getInt("if_kkanbu_Seq");
					int if_kkanbuSeqFrom = rs.getInt("if_kkanbuSeqFrom");
					int if_kkanbuSeqTo = rs.getInt("if_kkanbuSeqTo");
					String if_kkanbuNameFrom = rs.getString("if_kkanbuNameFrom");
					String  if_kkanbuNickNameTo = rs.getString("if_kkanbuNickNameTo");
					Timestamp requestedTime = rs.getTimestamp("if_requestedTime");
					int cp_kkanbuCardSeq = rs.getInt("cp_kkanbuCardSeq");
					String cp_title_cp = rs.getString("cp_title_cp");
					
					InfluencerKkanbuRequestDTO influencerKkanbuRequestDTO  = new InfluencerKkanbuRequestDTO(seq,if_kkanbuSeqFrom,if_kkanbuSeqTo,if_kkanbuNameFrom,if_kkanbuNickNameTo,requestedTime,cp_kkanbuCardSeq,cp_title_cp);
				
					list.add(influencerKkanbuRequestDTO);
				}
				return list;
			}
			
			}
	}
	public boolean isRequestStillPending(int kkanbuSeqFrom, int kkanbuCardSeq, int influencerSeq) throws SQLException, Exception {
		String sql = "SELECT * FROM influencerKkanbuRequest WHERE if_kkanbuSeqFrom=? and cp_kkanbuCardSeq =? and if_kkanbuseqto =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeqFrom);
			pstat.setInt(2,  kkanbuCardSeq);
			pstat.setInt(3,  influencerSeq);
			try(ResultSet rs = pstat.executeQuery();){
				boolean result = false;
				if(rs.next()) {
					result = true;
				
				}
				return result;
			}
			
			}
	
	}
	public int delete(int kkanbuSeq) throws SQLException, Exception {
		String sql = "DELETE FROM influencerKkanbuRequest WHERE if_kkanbu_seq=?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, kkanbuSeq);
			int result = pstat.executeUpdate();
			return result;
		}
	}
}


