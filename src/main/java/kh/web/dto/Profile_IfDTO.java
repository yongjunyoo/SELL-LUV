package kh.web.dto;

public class Profile_IfDTO {
	private int seq_if;
	private String writer_if;
	private String photo_if;
	private String condition_if;
	private String career_if;
	private String sns_if;
	private int sLike_if;
	private int rLike_if;
	private String review_if;
	
	public Profile_IfDTO() {

	}

	public Profile_IfDTO(int seq_if, String writer_if, String photo_if, String condition_if, String career_if,
			String sns_if, int sLike_if, int rLike_if, String review_if) {
		this.seq_if = seq_if;
		this.writer_if = writer_if;
		this.photo_if = photo_if;
		this.condition_if = condition_if;
		this.career_if = career_if;
		this.sns_if = sns_if;
		this.sLike_if = sLike_if;
		this.rLike_if = rLike_if;
		this.review_if = review_if;
	}

	public int getSeq_if() {
		return seq_if;
	}

	public void setSeq_if(int seq_if) {
		this.seq_if = seq_if;
	}

	public String getWriter_if() {
		return writer_if;
	}

	public void setWriter_if(String writer_if) {
		this.writer_if = writer_if;
	}

	public String getPhoto_if() {
		return photo_if;
	}

	public void setPhoto_if(String photo_if) {
		this.photo_if = photo_if;
	}

	public String getCondition_if() {
		return condition_if;
	}

	public void setCondition_if(String condition_if) {
		this.condition_if = condition_if;
	}

	public String getCareer_if() {
		return career_if;
	}

	public void setCareer_if(String career_if) {
		this.career_if = career_if;
	}

	public String getSns_if() {
		return sns_if;
	}

	public void setSns_if(String sns_if) {
		this.sns_if = sns_if;
	}

	public int getsLike_if() {
		return sLike_if;
	}

	public void setsLike_if(int sLike_if) {
		this.sLike_if = sLike_if;
	}

	public int getrLike_if() {
		return rLike_if;
	}

	public void setrLike_if(int rLike_if) {
		this.rLike_if = rLike_if;
	}

	public String getReview_if() {
		return review_if;
	}

	public void setReview_if(String review_if) {
		this.review_if = review_if;
	}
}
