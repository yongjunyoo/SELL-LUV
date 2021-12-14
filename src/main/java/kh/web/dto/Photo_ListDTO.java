package kh.web.dto;

public class Photo_ListDTO {
	private int seq_photo;
	private String oriName;
	private String sysName;
	private int parentSeq;
	public Photo_ListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Photo_ListDTO(int seq_photo, String oriName, String sysName, int parentSeq) {
		super();
		this.seq_photo = seq_photo;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentSeq = parentSeq;
	}
	public int getSeq_photo() {
		return seq_photo;
	}
	public void setSeq_photo(int seq_photo) {
		this.seq_photo = seq_photo;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public int getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}
	
}
