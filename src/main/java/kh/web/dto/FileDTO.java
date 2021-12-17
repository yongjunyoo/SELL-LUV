package kh.web.dto;

public class FileDTO {
	private int seq;
	private String oriName;
	private String sysName;
	private int parentSeq;
	public FileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileDTO(int seq, String oriName, String sysName, int parentSeq) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentSeq = parentSeq;
	}
	public final int getSeq() {
		return seq;
	}
	public final void setSeq(int seq) {
		this.seq = seq;
	}
	public final String getOriName() {
		return oriName;
	}
	public final void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public final String getSysName() {
		return sysName;
	}
	public final void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public final int getParentSeq() {
		return parentSeq;
	}
	public final void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	} 
	
}
