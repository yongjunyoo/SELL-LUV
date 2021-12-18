package kh.web.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	
	private int seq;
	private String writer;
	private String title;
	private String contents;
	private Timestamp write_date;
	private int view_count;
	private String profileName; // 추가된 컬럼

	public BoardDTO() {

	}
	
	public BoardDTO(int seq, String writer, String title, String contents, Timestamp write_date, int view_count,
			String profileName) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.write_date = write_date;
		this.view_count = view_count;
		this.profileName = profileName;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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