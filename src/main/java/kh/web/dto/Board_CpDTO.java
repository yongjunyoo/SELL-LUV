package kh.web.dto;

import java.sql.Timestamp;

public class Board_CpDTO {
	private int seq_cp;
	private String writer_cp;
	private String title_cp;
	private String condition_cp;
	private Timestamp write_date_cp;
	private int view_count_cp;
	private int sLike_cp;
	private int rLike_cp;
	private String review_cp;
	
	public Board_CpDTO() {

	}

	public Board_CpDTO(int seq_cp, String writer_cp, String title_cp, String condition_cp, Timestamp write_date_cp,
			int view_count_cp, int sLike_cp, int rLike_cp, String review_cp) {
		this.seq_cp = seq_cp;
		this.writer_cp = writer_cp;
		this.title_cp = title_cp;
		this.condition_cp = condition_cp;
		this.write_date_cp = write_date_cp;
		this.view_count_cp = view_count_cp;
		this.sLike_cp = sLike_cp;
		this.rLike_cp = rLike_cp;
		this.review_cp = review_cp;
	}

	public int getSeq_cp() {
		return seq_cp;
	}

	public void setSeq_cp(int seq_cp) {
		this.seq_cp = seq_cp;
	}

	public String getWriter_cp() {
		return writer_cp;
	}

	public void setWriter_cp(String writer_cp) {
		this.writer_cp = writer_cp;
	}

	public String getTitle_cp() {
		return title_cp;
	}

	public void setTitle_cp(String title_cp) {
		this.title_cp = title_cp;
	}

	public String getCondition_cp() {
		return condition_cp;
	}

	public void setCondition_cp(String condition_cp) {
		this.condition_cp = condition_cp;
	}

	public Timestamp getWrite_date_cp() {
		return write_date_cp;
	}

	public void setWrite_date_cp(Timestamp write_date_cp) {
		this.write_date_cp = write_date_cp;
	}

	public int getView_count_cp() {
		return view_count_cp;
	}

	public void setView_count_cp(int view_count_cp) {
		this.view_count_cp = view_count_cp;
	}

	public int getsLike_cp() {
		return sLike_cp;
	}

	public void setsLike_cp(int sLike_cp) {
		this.sLike_cp = sLike_cp;
	}

	public int getrLike_cp() {
		return rLike_cp;
	}

	public void setrLike_cp(int rLike_cp) {
		this.rLike_cp = rLike_cp;
	}

	public String getReview_cp() {
		return review_cp;
	}

	public void setReview_cp(String review_cp) {
		this.review_cp = review_cp;
	}
}
