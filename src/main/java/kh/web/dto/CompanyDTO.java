package kh.web.dto;

public class CompanyDTO {

	int seq_cp;
	String id_cp;
	String pw_cp;
	String photo_cp;
	String name_cp;
	String crnumber_cp;
	String zipcode_cp;
	String address1_cp;
	String address2_cp;
	String rpt_cp;
	String phone_cp;
	String email_cp;
	Long sales_cp;
	
	CompanyDTO(){}

	public CompanyDTO(int seq_cp, String id_cp, String pw_cp, String photo_cp, String name_cp, String crnumber_cp,
			String zipcode_cp, String address1_cp, String address2_cp, String rpt_cp, String phone_cp, String email_cp,
			Long sales_cp) {
		super();
		this.seq_cp = seq_cp;
		this.id_cp = id_cp;
		this.pw_cp = pw_cp;
		this.photo_cp = photo_cp;
		this.name_cp = name_cp;
		this.crnumber_cp = crnumber_cp;
		this.zipcode_cp = zipcode_cp;
		this.address1_cp = address1_cp;
		this.address2_cp = address2_cp;
		this.rpt_cp = rpt_cp;
		this.phone_cp = phone_cp;
		this.email_cp = email_cp;
		this.sales_cp = sales_cp;
	}

	public int getSeq_cp() {
		return seq_cp;
	}

	public void setSeq_cp(int seq_cp) {
		this.seq_cp = seq_cp;
	}

	public String getId_cp() {
		return id_cp;
	}

	public void setId_cp(String id_cp) {
		this.id_cp = id_cp;
	}

	public String getPw_cp() {
		return pw_cp;
	}

	public void setPw_cp(String pw_cp) {
		this.pw_cp = pw_cp;
	}

	public String getPhoto_cp() {
		return photo_cp;
	}

	public void setPhoto_cp(String photo_cp) {
		this.photo_cp = photo_cp;
	}

	public String getName_cp() {
		return name_cp;
	}

	public void setName_cp(String name_cp) {
		this.name_cp = name_cp;
	}

	public String getCrnumber_cp() {
		return crnumber_cp;
	}

	public void setCrnumber_cp(String crnumber_cp) {
		this.crnumber_cp = crnumber_cp;
	}

	public String getZipcode_cp() {
		return zipcode_cp;
	}

	public void setZipcode_cp(String zipcode_cp) {
		this.zipcode_cp = zipcode_cp;
	}

	public String getAddress1_cp() {
		return address1_cp;
	}

	public void setAddress1_cp(String address1_cp) {
		this.address1_cp = address1_cp;
	}

	public String getAddress2_cp() {
		return address2_cp;
	}

	public void setAddress2_cp(String address2_cp) {
		this.address2_cp = address2_cp;
	}

	public String getRpt_cp() {
		return rpt_cp;
	}

	public void setRpt_cp(String rpt_cp) {
		this.rpt_cp = rpt_cp;
	}

	public String getPhone_cp() {
		return phone_cp;
	}

	public void setPhone_cp(String phone_cp) {
		this.phone_cp = phone_cp;
	}

	public String getEmail_cp() {
		return email_cp;
	}

	public void setEmail_cp(String email_cp) {
		this.email_cp = email_cp;
	}

	public Long getSales_cp() {
		return sales_cp;
	}

	public void setSales_cp(Long sales_cp) {
		this.sales_cp = sales_cp;
	}
	
	
	
	
}
