package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.BoardDTO;

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
	public int sendKkanbuRequest(int kkanbuSeqFrom,int kkanbuSeqTo, String kkanbuNameFrom, String kkanbuNickNameTo) throws SQLException, Exception {
		
			String sql = "INSERT INTO InfluencerKkanbuRequest VALUES (if_kkanbu_seq.nextval,?,?,?,?,DEFAULT)";
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setInt(1, kkanbuSeqFrom);
				pstat.setInt(2, kkanbuSeqTo);
				pstat.setString(3, kkanbuNameFrom);
				pstat.setString(4, kkanbuNickNameTo);
				int result = pstat.executeUpdate();
				return result;
			}
		}
	
	
	}


