package kh.web.dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	private int seq;
	private String writer;
	private String title;
	private String contents;
	private Timestamp write_date;
	private int view_count;
	
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(int seq, String writer, String title, String contents, Timestamp write_date, int view_count) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.write_date = write_date;
		this.view_count = view_count;
	}
	public final int getSeq() {
		return seq;
	}
	public final void setSeq(int seq) {
		this.seq = seq;
	}
	public final String getWriter() {
		return writer;
	}
	public final void setWriter(String writer) {
		this.writer = writer;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final String getContents() {
		return contents;
	}
	public final void setContents(String contents) {
		this.contents = contents;
	}
	public final Timestamp getWrite_date() {
		return write_date;
	}
	public final void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public final int getView_count() {
		return view_count;
	}
	public final void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
}