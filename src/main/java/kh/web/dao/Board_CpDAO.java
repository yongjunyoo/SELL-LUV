package kh.web.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Board_CpDAO {

	private static Board_CpDAO instance = null;

	public static Board_CpDAO getInstance() {
		if(instance==null) {
			instance = new Board_CpDAO();
		}
		return instance;
	}
	public Board_CpDAO() {

	}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

}
