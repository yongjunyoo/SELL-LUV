package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
}	
