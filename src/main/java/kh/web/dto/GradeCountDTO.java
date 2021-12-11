package kh.web.dto;

public class GradeCountDTO {
	private int goldcount;
	private int silvercount;
	private int bronzecount;
	
	public GradeCountDTO() {

	}

	public GradeCountDTO(int goldcount, int silvercount, int bronzecount) {
		this.goldcount = goldcount;
		this.silvercount = silvercount;
		this.bronzecount = bronzecount;
	}

	public int getGoldcount() {
		return goldcount;
	}

	public void setGoldcount(int goldcount) {
		this.goldcount = goldcount;
	}

	public int getSilvercount() {
		return silvercount;
	}

	public void setSilvercount(int silvercount) {
		this.silvercount = silvercount;
	}

	public int getBronzecount() {
		return bronzecount;
	}

	public void setBronzecount(int bronzecount) {
		this.bronzecount = bronzecount;
	}
}
