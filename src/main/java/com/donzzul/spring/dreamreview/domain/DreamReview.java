package com.donzzul.spring.dreamreview.domain;

import java.sql.Date;

public class DreamReview {
	private int dviewNo;
	private String dReviewTitle;
	private String dReviewContent;
	private Date dReviewDate;
	private String dReviewPublicYN;
	private String userType;
	private int userNo;
	private int shopNo;
	
	public DreamReview() {}

	public DreamReview(int dviewNo, String dReviewTitle, String dReviewContent, Date dReviewDate,
			String dReviewPublicYN, String userType, int userNo, int shopNo) {
		super();
		this.dviewNo = dviewNo;
		this.dReviewTitle = dReviewTitle;
		this.dReviewContent = dReviewContent;
		this.dReviewDate = dReviewDate;
		this.dReviewPublicYN = dReviewPublicYN;
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
	}

	public int getDviewNo() {
		return dviewNo;
	}

	public void setDviewNo(int dviewNo) {
		this.dviewNo = dviewNo;
	}

	public String getdReviewTitle() {
		return dReviewTitle;
	}

	public void setdReviewTitle(String dReviewTitle) {
		this.dReviewTitle = dReviewTitle;
	}

	public String getdReviewContent() {
		return dReviewContent;
	}

	public void setdReviewContent(String dReviewContent) {
		this.dReviewContent = dReviewContent;
	}

	public Date getdReviewDate() {
		return dReviewDate;
	}

	public void setdReviewDate(Date dReviewDate) {
		this.dReviewDate = dReviewDate;
	}

	public String getdReviewPublicYN() {
		return dReviewPublicYN;
	}

	public void setdReviewPublicYN(String dReviewPublicYN) {
		this.dReviewPublicYN = dReviewPublicYN;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	@Override
	public String toString() {
		return "DreamReview [dviewNo=" + dviewNo + ", dReviewTitle=" + dReviewTitle + ", dReviewContent="
				+ dReviewContent + ", dReviewDate=" + dReviewDate + ", dReviewPublicYN=" + dReviewPublicYN
				+ ", userType=" + userType + ", userNo=" + userNo + ", shopNo=" + shopNo + "]";
	}

	
}
