package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.InfluencerDTO;

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
	
	
	public List getListByTable(String member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
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

}
