package com.pd.model;

import java.sql.Timestamp;

public class PdVO {

	private int no;
	private String pdName;
	private int price;
	private Timestamp regdate;
	private int newImgTerm;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getNewImgTerm() {
		return newImgTerm;
	}

	public void setNewImgTerm(int newImgTerm) {
		this.newImgTerm = newImgTerm;
	}

	@Override
	public String toString() {
		return "PdVO [no=" + no + ", pdName=" + pdName + ", price=" + price + ", regdate=" + regdate + ", newImgTerm="
				+ newImgTerm + "]";
	}

}
