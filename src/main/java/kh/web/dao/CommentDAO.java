package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.dto.BoardDTO;
import kh.web.dto.CommentDTO;

public class CommentDAO  {
	
private static CommentDAO instance = null;
	
	public static CommentDAO getInstance() {
		if(instance==null) {
			instance = new CommentDAO();
		}
		return instance;
	}
	
	public CommentDAO() {}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int insert(CommentDTO dto) throws Exception {
		String sql = "INSERT INTO board_comment VALUES (board_comment_seq.nextval,DEFAULT,?,DEFAULT,?,?,?,null)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setInt(2, dto.getParent());
			pstat.setString(3, dto.getContents());
			pstat.setString(4, dto.getMember());
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int modify(int seq, String contents) throws Exception {
		String sql = "UPDATE board_comment SET comment_content=? WHERE comment_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, contents);
			pstat.setInt(2, seq);
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int delete(int seq) throws Exception {
		String sql = "DELETE FROM board_comment WHERE comment_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public List<CommentDTO> selectAll() throws Exception {
		String sql = "SELECT * FROM board_comment ORDER BY 1";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){
			List<CommentDTO> list = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt("comment_seq");
				int board = rs.getInt("comment_board");
				String writer = rs.getString("comment_writer");
				Timestamp write_date = rs.getTimestamp("comment_date");
				int parent = rs.getInt("comment_parent");
				String contents = rs.getString("comment_content");
				String member = rs.getString("comment_member");
				String id = rs.getString("comment_id");
				list.add(new CommentDTO(seq,board,writer,write_date,parent,contents,member,id));
			}
			return list;
			}
	}
	
	public List<CommentDTO> selectByBoardSeq(int pseq) throws Exception {
		String sql = "SELECT * FROM board_comment WHERE comment_parent = ? ORDER BY 1";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, pseq);
			try(ResultSet rs = pstat.executeQuery();){
				List<CommentDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("comment_seq");
					int board = rs.getInt("comment_board");
					String writer = rs.getString("comment_writer");
					Timestamp write_date = rs.getTimestamp("comment_date");
					int parent = rs.getInt("comment_parent");
					String contents = rs.getString("comment_content");
					String member = rs.getString("comment_member");
					String id = rs.getString("comment_id");
					list.add(new CommentDTO(seq,board,writer,write_date,parent,contents,member,id));
				}
				return list;
			}
		}
	}
	
	public List<CommentDTO> selectByBoardSeqAddName(int pseq) throws Exception {
		String sql = "SELECT * FROM board_comment bc JOIN \r\n"
				+ "(SELECT c.id_cp , c.name_cp member_name FROM company c UNION SELECT  i.id_if, i.nickname_if FROM influencer i)\r\n"
				+ "ON bc.comment_writer = id_cp WHERE comment_parent = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, pseq);
			try(ResultSet rs = pstat.executeQuery();){
				List<CommentDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("comment_seq");
					int board = rs.getInt("comment_board");
					String writer = rs.getString("comment_writer");
					Timestamp write_date = rs.getTimestamp("comment_date");
					int parent = rs.getInt("comment_parent");
					String contents = rs.getString("comment_content");
					String member = rs.getString("comment_member");
					String profileName = rs.getString("member_name");
					list.add(new CommentDTO(seq,board,writer,write_date,parent,contents,member,profileName));
				}
				return list;
			}
		}
	}
	
	
	
	
}
