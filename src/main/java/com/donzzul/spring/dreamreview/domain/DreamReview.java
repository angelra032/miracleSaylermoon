package com.donzzul.spring.dreamreview.domain;

import java.sql.Date;

public class DreamReview {
	private int drmRviewNo;
	private String drmReviewTitle;
	private String drmReviewContent;
	private String drmReviewWriter;
	private Date drmReviewCreateDate;
	private Date drmReviewUploadDate;
	private String drmReviewPublicYN;
	private String userType;
	private int userNo;
	private int shopNo;
	private String shopName;
	private int drmRviewHit;
	
	public DreamReview() {}

	public DreamReview(String drmReviewTitle, String drmReviewContent) {
		super();
		this.drmReviewTitle = drmReviewTitle;
		this.drmReviewContent = drmReviewContent;
	}

	public DreamReview(int drmRviewNo, String drmReviewTitle, String drmReviewContent, String drmReviewWriter,
			Date drmReviewCreateDate, Date drmReviewUploadDate, String drmReviewPublicYN, String userType, int userNo,
			int shopNo, String shopName, int drmRviewHit) {
		super();
		this.drmRviewNo = drmRviewNo;
		this.drmReviewTitle = drmReviewTitle;
		this.drmReviewContent = drmReviewContent;
		this.drmReviewWriter = drmReviewWriter;
		this.drmReviewCreateDate = drmReviewCreateDate;
		this.drmReviewUploadDate = drmReviewUploadDate;
		this.drmReviewPublicYN = drmReviewPublicYN;
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
		this.shopName = shopName;
		this.drmRviewHit = drmRviewHit;
	}

	public int getDrmRviewNo() {
		return drmRviewNo;
	}

	public void setDrmRviewNo(int drmRviewNo) {
		this.drmRviewNo = drmRviewNo;
	}

	public String getDrmReviewTitle() {
		return drmReviewTitle;
	}

	public void setDrmReviewTitle(String drmReviewTitle) {
		this.drmReviewTitle = drmReviewTitle;
	}

	public String getDrmReviewContent() {
		return drmReviewContent;
	}

	public void setDrmReviewContent(String drmReviewContent) {
		this.drmReviewContent = drmReviewContent;
	}

	public String getDrmReviewWriter() {
		return drmReviewWriter;
	}

	public void setDrmReviewWriter(String drmReviewWriter) {
		this.drmReviewWriter = drmReviewWriter;
	}

	public Date getDrmReviewCreateDate() {
		return drmReviewCreateDate;
	}

	public void setDrmReviewCreateDate(Date drmReviewCreateDate) {
		this.drmReviewCreateDate = drmReviewCreateDate;
	}

	public Date getDrmReviewUploadDate() {
		return drmReviewUploadDate;
	}

	public void setDrmReviewUploadDate(Date drmReviewUploadDate) {
		this.drmReviewUploadDate = drmReviewUploadDate;
	}

	public String getDrmReviewPublicYN() {
		return drmReviewPublicYN;
	}

	public void setDrmReviewPublicYN(String drmReviewPublicYN) {
		this.drmReviewPublicYN = drmReviewPublicYN;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getDrmRviewHit() {
		return drmRviewHit;
	}

	public void setDrmRviewHit(int drmRviewHit) {
		this.drmRviewHit = drmRviewHit;
	}

	@Override
	public String toString() {
		return "DreamReview [drmRviewNo=" + drmRviewNo + ", drmReviewTitle=" + drmReviewTitle + ", drmReviewContent="
				+ drmReviewContent + ", drmReviewWriter=" + drmReviewWriter + ", drmReviewCreateDate="
				+ drmReviewCreateDate + ", drmReviewUploadDate=" + drmReviewUploadDate + ", drmReviewPublicYN="
				+ drmReviewPublicYN + ", userType=" + userType + ", userNo=" + userNo + ", shopNo=" + shopNo
				+ ", shopName=" + shopName + ", drmRviewHit=" + drmRviewHit + "]";
	}


}
