package kh.web.dto;

public class CompanyDTO {


	private int seq;
	private String id;
	private String pw;
	private String photo;
	private String name;
	private String crnumber;
	private String zipcode;
	private String address1;
	private String address2;
	private String rpt;
	private String phone;
	private String email;
	private Long sales;
	private String grade;
	private String pwAsk;
	private String pwAnswer;
	
	public CompanyDTO() {}
	
	
	public CompanyDTO(int seq, String id, String pw, String photo, String name, String crnumber, String zipcode,
			String address1, String address2, String rpt, String phone, String email, Long sales, String grade,
			String pwAsk, String pwAnswer) {
		super();
		this.seq = seq;
		this.id = id;
		this.pw = pw;
		this.photo = photo;
		this.name = name;
		this.crnumber = crnumber;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.rpt = rpt;
		this.phone = phone;
		this.email = email;
		this.sales = sales;
		this.grade = grade;
		this.pwAsk = pwAsk;
		this.pwAnswer = pwAnswer;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCrnumber() {
		return crnumber;
	}


	public void setCrnumber(String crnumber) {
		this.crnumber = crnumber;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getRpt() {
		return rpt;
	}


	public void setRpt(String rpt) {
		this.rpt = rpt;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getSales() {
		return sales;
	}


	public void setSales(Long sales) {
		this.sales = sales;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getPwAsk() {
		return pwAsk;
	}


	public void setPwAsk(String pwAsk) {
		this.pwAsk = pwAsk;
	}


	public String getPwAnswer() {
		return pwAnswer;
	}


	public void setPwAnswer(String pwAnswer) {
		this.pwAnswer = pwAnswer;
	}
	
	

	
	

	
	
	
}
