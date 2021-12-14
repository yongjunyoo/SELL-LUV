package kh.web.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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

}
