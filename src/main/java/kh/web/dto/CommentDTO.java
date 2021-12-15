package kh.web.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentDTO {
	private int seq;
	private int board;
	private String writer;
	private Timestamp write_date;
	private int parent;
	private String contents;
	
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDTO(int seq, int board, String writer, Timestamp write_date, int parent, String contents) {
		super();
		this.seq = seq;
		this.board = board;
		this.writer = writer;
		this.write_date = write_date;
		this.parent = parent;
		this.contents = contents;
	}
	public final int getSeq() {
		return seq;
	}
	public final void setSeq(int seq) {
		this.seq = seq;
	}
	public final int getBoard() {
		return board;
	}
	public final void setBoard(int board) {
		this.board = board;
	}
	public final String getWriter() {
		return writer;
	}
	public final void setWriter(String writer) {
		this.writer = writer;
	}
	public final Timestamp getWrite_date() {
		return write_date;
	}
	public final void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public final int getParent() {
		return parent;
	}
	public final void setParent(int parent) {
		this.parent = parent;
	}
	public final String getContents() {
		return contents;
	}
	public final void setContents(String contents) {
		this.contents = contents;
	}
	
	// 날짜 가공해서 출력
	public String getFormedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return sdf.format(write_date.getTime());
	}
	
	// 24시간 안으로 몇 시간전 출력, 1시간 안으로 몇 분전 출력
	public String getDetailDate() {
		long current_time = System.currentTimeMillis(); // 현재 Timestamp
		long write_time = this.write_date.getTime();  // 글이 작성된 시점의 Timestamp
		
		long time_gap = current_time - write_time;
		
		if(time_gap < 1000*60) {
			return "방금 전"; 
		}else if(time_gap < 1000*60*60) {
			return time_gap/1000/60 + "분 전"; 
		}else if(time_gap < 1000*60*60*24) {
			return time_gap/1000/60/60 + "시간 전";
		}else {
			return this.getFormedDate();
		}
	}
}
