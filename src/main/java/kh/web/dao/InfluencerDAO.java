package kh.web.dao;

import java.sql.Connection;
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

}
