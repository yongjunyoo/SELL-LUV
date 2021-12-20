package kh.web.dto;

import java.sql.Timestamp;

public class InfluencerKkanbuRequestDTO {

	private int if_kkanbu_seq;
    private int if_kkanbuSeqFrom;
    private int if_kkanbuSeqTo;
    private String if_kkanbuNameFrom; 
    private String if_kkanbuNickNameTo;
    private Timestamp if_requestedTime;
    private int cp_kkanbuCardSeq;
    private String cp_title_cp; 
    
    public InfluencerKkanbuRequestDTO(){}

	public InfluencerKkanbuRequestDTO(int if_kkanbu_seq, int if_kkanbuSeqFrom, int if_kkanbuSeqTo,
			String if_kkanbuNameFrom, String if_kkanbuNickNameTo, Timestamp if_requestedTime, int cp_kkanbuCardSeq,
			String cp_title_cp) {
		super();
		this.if_kkanbu_seq = if_kkanbu_seq;
		this.if_kkanbuSeqFrom = if_kkanbuSeqFrom;
		this.if_kkanbuSeqTo = if_kkanbuSeqTo;
		this.if_kkanbuNameFrom = if_kkanbuNameFrom;
		this.if_kkanbuNickNameTo = if_kkanbuNickNameTo;
		this.if_requestedTime = if_requestedTime;
		this.cp_kkanbuCardSeq = cp_kkanbuCardSeq;
		this.cp_title_cp = cp_title_cp;
	}

	public int getIf_kkanbu_seq() {
		return if_kkanbu_seq;
	}

	public void setIf_kkanbu_seq(int if_kkanbu_seq) {
		this.if_kkanbu_seq = if_kkanbu_seq;
	}

	public int getIf_kkanbuSeqFrom() {
		return if_kkanbuSeqFrom;
	}

	public void setIf_kkanbuSeqFrom(int if_kkanbuSeqFrom) {
		this.if_kkanbuSeqFrom = if_kkanbuSeqFrom;
	}

	public int getIf_kkanbuSeqTo() {
		return if_kkanbuSeqTo;
	}

	public void setIf_kkanbuSeqTo(int if_kkanbuSeqTo) {
		this.if_kkanbuSeqTo = if_kkanbuSeqTo;
	}

	public String getIf_kkanbuNameFrom() {
		return if_kkanbuNameFrom;
	}

	public void setIf_kkanbuNameFrom(String if_kkanbuNameFrom) {
		this.if_kkanbuNameFrom = if_kkanbuNameFrom;
	}

	public String getIf_kkanbuNickNameTo() {
		return if_kkanbuNickNameTo;
	}

	public void setIf_kkanbuNickNameTo(String if_kkanbuNickNameTo) {
		this.if_kkanbuNickNameTo = if_kkanbuNickNameTo;
	}

	public Timestamp getIf_requestedTime() {
		return if_requestedTime;
	}

	public void setIf_requestedTime(Timestamp if_requestedTime) {
		this.if_requestedTime = if_requestedTime;
	}

	public int getCp_kkanbuCardSeq() {
		return cp_kkanbuCardSeq;
	}

	public void setCp_kkanbuCardSeq(int cp_kkanbuCardSeq) {
		this.cp_kkanbuCardSeq = cp_kkanbuCardSeq;
	}

	public String getCp_title_cp() {
		return cp_title_cp;
	}

	public void setCp_title_cp(String cp_title_cp) {
		this.cp_title_cp = cp_title_cp;
	}

	

    
}
