package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.BoardDTO;
import kh.web.dto.FileDTO;

public class FileDAO {
	
	private static FileDAO instance = null;
	
	public static FileDAO getInstance() {
		if(instance==null) {
			instance = new FileDAO();
		}
		return instance;
	}
	
	public FileDAO() {}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int insertCp(FileDTO dto) throws Exception {
		String sql = "INSERT INTO file_company VALUES (file_company_seq.nextval,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParentSeq());
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int insertIf(FileDTO dto) throws Exception {
		String sql = "INSERT INTO file_influencer VALUES (file_influencer_seq.nextval,?,?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParentSeq());
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	
	
	
	
	
	
	
}
