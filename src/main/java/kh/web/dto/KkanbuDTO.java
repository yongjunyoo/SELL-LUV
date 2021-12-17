package kh.web.dto;

public class KkanbuDTO {

	int kkanbu_seq;
    int influencer_seq;
    int company_seq;
    
    public KkanbuDTO() {}
    
	public KkanbuDTO(int kkanbu_seq, int influencer_seq, int company_seq) {
		super();
		this.kkanbu_seq = kkanbu_seq;
		this.influencer_seq = influencer_seq;
		this.company_seq = company_seq;
	}
	public int getKkanbu_seq() {
		return kkanbu_seq;
	}
	public void setKkanbu_seq(int kkanbu_seq) {
		this.kkanbu_seq = kkanbu_seq;
	}
	public int getInfluencer_seq() {
		return influencer_seq;
	}
	public void setInfluencer_seq(int influencer_seq) {
		this.influencer_seq = influencer_seq;
	}
	public int getCompany_seq() {
		return company_seq;
	}
	public void setCompany_seq(int company_seq) {
		this.company_seq = company_seq;
	}
    
    
}
