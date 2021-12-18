package kh.web.dto;

import java.sql.Timestamp;

public class Review_IfDTO {

	private int seq;
	private int member_seq;
	private String writer;
	private String content;
	private Timestamp timestamp;
	private int ref_seq;
	
	public Review_IfDTO(){}

	public Review_IfDTO(int seq, int member_seq, String writer, String content, Timestamp timestamp, int ref_seq) {
		super();
		this.seq = seq;
		this.member_seq = member_seq;
		this.writer = writer;
		this.content = content;
		this.timestamp = timestamp;
		this.ref_seq = ref_seq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getRef_seq() {
		return ref_seq;
	}

	public void setRef_seq(int ref_seq) {
		this.ref_seq = ref_seq;
	}

	
}
