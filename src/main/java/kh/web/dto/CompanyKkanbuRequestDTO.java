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
    
    public CompanyKkanbuRequestDTO() {}

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

	public int getCp_kkanbu_seq() {
		return cp_kkanbu_seq;
	}

	public void setCp_kkanbu_seq(int cp_kkanbu_seq) {
		this.cp_kkanbu_seq = cp_kkanbu_seq;
	}

	public int getCp_kkanbuSeqFrom() {
		return cp_kkanbuSeqFrom;
	}

	public void setCp_kkanbuSeqFrom(int cp_kkanbuSeqFrom) {
		this.cp_kkanbuSeqFrom = cp_kkanbuSeqFrom;
	}

	public int getCp_kkanbuSeqTo() {
		return cp_kkanbuSeqTo;
	}

	public void setCp_kkanbuSeqTo(int cp_kkanbuSeqTo) {
		this.cp_kkanbuSeqTo = cp_kkanbuSeqTo;
	}

	public String getCp_kkanbuNameFrom() {
		return cp_kkanbuNameFrom;
	}

	public void setCp_kkanbuNameFrom(String cp_kkanbuNameFrom) {
		this.cp_kkanbuNameFrom = cp_kkanbuNameFrom;
	}

	public String getCp_kkanbuNickNameTo() {
		return cp_kkanbuNickNameTo;
	}

	public void setCp_kkanbuNickNameTo(String cp_kkanbuNickNameTo) {
		this.cp_kkanbuNickNameTo = cp_kkanbuNickNameTo;
	}

	public Timestamp getCp_requestedTime() {
		return cp_requestedTime;
	}

	public void setCp_requestedTime(Timestamp cp_requestedTime) {
		this.cp_requestedTime = cp_requestedTime;
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
