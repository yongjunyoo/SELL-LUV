package kh.web.dto;

import java.sql.Timestamp;

public class ReviewDTO {

	int review_seq;
	String review_writer;
	String review_content;
	Timestamp review_timestamp;
	
	ReviewDTO(){}

	public ReviewDTO(int review_seq, String review_writer, String review_content, Timestamp review_timestamp) {
		super();
		this.review_seq = review_seq;
		this.review_writer = review_writer;
		this.review_content = review_content;
		this.review_timestamp = review_timestamp;
	}

	public int getReview_seq() {
		return review_seq;
	}

	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
	}

	public String getReview_writer() {
		return review_writer;
	}

	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public Timestamp getReview_timestamp() {
		return review_timestamp;
	}

	public void setReview_timestamp(Timestamp review_timestamp) {
		this.review_timestamp = review_timestamp;
	}

	
	
}