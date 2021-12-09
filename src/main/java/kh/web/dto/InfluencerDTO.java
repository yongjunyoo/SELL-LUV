package kh.web.dto;

public class InfluencerDTO {

	int seq_if;
	String id_if;
	String pw_if;
	String photo_if;
	String name_if;
	String nickname_if;
	String zipcode_if;
	String address1_if;
	String address2_if;
	String sns_if;
	String phone_if;
	String email_if;
	
	public InfluencerDTO() {}
	
	public InfluencerDTO(int seq_if, String id_if, String pw_if, String photo_if, String name_if, String nickname_if,
			String zipcode_if, String address1_if, String address2_if, String sns_if, String phone_if,
			String email_if) {
		super();
		this.seq_if = seq_if;
		this.id_if = id_if;
		this.pw_if = pw_if;
		this.photo_if = photo_if;
		this.name_if = name_if;
		this.nickname_if = nickname_if;
		this.zipcode_if = zipcode_if;
		this.address1_if = address1_if;
		this.address2_if = address2_if;
		this.sns_if = sns_if;
		this.phone_if = phone_if;
		this.email_if = email_if;
	}

	public int getSeq_if() {
		return seq_if;
	}

	public void setSeq_if(int seq_if) {
		this.seq_if = seq_if;
	}

	public String getId_if() {
		return id_if;
	}

	public void setId_if(String id_if) {
		this.id_if = id_if;
	}

	public String getPw_if() {
		return pw_if;
	}

	public void setPw_if(String pw_if) {
		this.pw_if = pw_if;
	}

	public String getPhoto_if() {
		return photo_if;
	}

	public void setPhoto_if(String photo_if) {
		this.photo_if = photo_if;
	}

	public String getName_if() {
		return name_if;
	}

	public void setName_if(String name_if) {
		this.name_if = name_if;
	}

	public String getNickname_if() {
		return nickname_if;
	}

	public void setNickname_if(String nickname_if) {
		this.nickname_if = nickname_if;
	}

	public String getZipcode_if() {
		return zipcode_if;
	}

	public void setZipcode_if(String zipcode_if) {
		this.zipcode_if = zipcode_if;
	}

	public String getAddress1_if() {
		return address1_if;
	}

	public void setAddress1_if(String address1_if) {
		this.address1_if = address1_if;
	}

	public String getAddress2_if() {
		return address2_if;
	}

	public void setAddress2_if(String address2_if) {
		this.address2_if = address2_if;
	}

	public String getSns_if() {
		return sns_if;
	}

	public void setSns_if(String sns_if) {
		this.sns_if = sns_if;
	}

	public String getPhone_if() {
		return phone_if;
	}

	public void setPhone_if(String phone_if) {
		this.phone_if = phone_if;
	}

	public String getEmail_if() {
		return email_if;
	}

	public void setEmail_if(String email_if) {
		this.email_if = email_if;
	}
	
	
	
}
