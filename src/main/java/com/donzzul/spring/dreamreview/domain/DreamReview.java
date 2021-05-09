package com.donzzul.spring.dreamreview.domain;

import java.sql.Date;

public class DreamReview {
	private int dreamreviewNo;
	private String dreamReviewTitle;
	private String dreamReviewContent;
	private Date dreamReviewDate;
	private String dreamReviewPublicYN;
	private String userType;
	private int userNo;
	private int shopNo;
	
	public DreamReview() {}

	public DreamReview(int dreamreviewNo, String dreamReviewTitle, String dreamReviewContent, Date dreamReviewDate,
			String dreamReviewPublicYN, String userType, int userNo, int shopNo) {
		super();
		this.dreamreviewNo = dreamreviewNo;
		this.dreamReviewTitle = dreamReviewTitle;
		this.dreamReviewContent = dreamReviewContent;
		this.dreamReviewDate = dreamReviewDate;
		this.dreamReviewPublicYN = dreamReviewPublicYN;
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
	}

	public int getDreamreviewNo() {
		return dreamreviewNo;
	}

	public void setDreamreviewNo(int dreamreviewNo) {
		this.dreamreviewNo = dreamreviewNo;
	}

	public String getDreamReviewTitle() {
		return dreamReviewTitle;
	}

	public void setDreamReviewTitle(String dreamReviewTitle) {
		this.dreamReviewTitle = dreamReviewTitle;
	}

	public String getDreamReviewContent() {
		return dreamReviewContent;
	}

	public void setDreamReviewContent(String dreamReviewContent) {
		this.dreamReviewContent = dreamReviewContent;
	}

	public Date getDreamReviewDate() {
		return dreamReviewDate;
	}

	public void setDreamReviewDate(Date dreamReviewDate) {
		this.dreamReviewDate = dreamReviewDate;
	}

	public String getDreamReviewPublicYN() {
		return dreamReviewPublicYN;
	}

	public void setDreamReviewPublicYN(String dreamReviewPublicYN) {
		this.dreamReviewPublicYN = dreamReviewPublicYN;
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
		return "DreamReview [dreamreviewNo=" + dreamreviewNo + ", dreamReviewTitle=" + dreamReviewTitle
				+ ", dreamReviewContent=" + dreamReviewContent + ", dreamReviewDate=" + dreamReviewDate
				+ ", dreamReviewPublicYN=" + dreamReviewPublicYN + ", userType=" + userType + ", userNo=" + userNo
				+ ", shopNo=" + shopNo + "]";
	}
	
	
}
