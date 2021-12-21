package kh.web.dto;

import java.sql.Timestamp;

public class CompanyKkanbuRequestDTO {

	private int cp_kkanbu_seq;
    private int cp_kkanbuSeqFrom;
    private int cp_kkanbuSeqTo;
    private String cp_kkanbuNameFrom; 
    private String cp_kkanbuNickNameTo;
    private Timestamp cp_requestedTime;
    private int cp_kkanbuCardSeq;
    private String cp_title_cp;
    
	public CompanyKkanbuRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompanyKkanbuRequestDTO(int cp_kkanbu_seq, int cp_kkanbuSeqFrom, int cp_kkanbuSeqTo,
			String cp_kkanbuNameFrom, String cp_kkanbuNickNameTo, Timestamp cp_requestedTime, int cp_kkanbuCardSeq,
			String cp_title_cp) {
		super();
		this.cp_kkanbu_seq = cp_kkanbu_seq;
		this.cp_kkanbuSeqFrom = cp_kkanbuSeqFrom;
		this.cp_kkanbuSeqTo = cp_kkanbuSeqTo;
		this.cp_kkanbuNameFrom = cp_kkanbuNameFrom;
		this.cp_kkanbuNickNameTo = cp_kkanbuNickNameTo;
		this.cp_requestedTime = cp_requestedTime;
		this.cp_kkanbuCardSeq = cp_kkanbuCardSeq;
		this.cp_title_cp = cp_title_cp;
	}
	public final int getCp_kkanbu_seq() {
		return cp_kkanbu_seq;
	}
	public final void setCp_kkanbu_seq(int cp_kkanbu_seq) {
		this.cp_kkanbu_seq = cp_kkanbu_seq;
	}
	public final int getCp_kkanbuSeqFrom() {
		return cp_kkanbuSeqFrom;
	}
	public final void setCp_kkanbuSeqFrom(int cp_kkanbuSeqFrom) {
		this.cp_kkanbuSeqFrom = cp_kkanbuSeqFrom;
	}
	public final int getCp_kkanbuSeqTo() {
		return cp_kkanbuSeqTo;
	}
	public final void setCp_kkanbuSeqTo(int cp_kkanbuSeqTo) {
		this.cp_kkanbuSeqTo = cp_kkanbuSeqTo;
	}
	public final String getCp_kkanbuNameFrom() {
		return cp_kkanbuNameFrom;
	}
	public final void setCp_kkanbuNameFrom(String cp_kkanbuNameFrom) {
		this.cp_kkanbuNameFrom = cp_kkanbuNameFrom;
	}
	public final String getCp_kkanbuNickNameTo() {
		return cp_kkanbuNickNameTo;
	}
	public final void setCp_kkanbuNickNameTo(String cp_kkanbuNickNameTo) {
		this.cp_kkanbuNickNameTo = cp_kkanbuNickNameTo;
	}
	public final Timestamp getCp_requestedTime() {
		return cp_requestedTime;
	}
	public final void setCp_requestedTime(Timestamp cp_requestedTime) {
		this.cp_requestedTime = cp_requestedTime;
	}
	public final int getCp_kkanbuCardSeq() {
		return cp_kkanbuCardSeq;
	}
	public final void setCp_kkanbuCardSeq(int cp_kkanbuCardSeq) {
		this.cp_kkanbuCardSeq = cp_kkanbuCardSeq;
	}
	public final String getCp_title_cp() {
		return cp_title_cp;
	}
	public final void setCp_title_cp(String cp_title_cp) {
		this.cp_title_cp = cp_title_cp;
	}
    
    
	
	
	
    
}
