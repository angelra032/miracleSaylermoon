package com.donzzul.spring.mzreview.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class MzReview {
	private int mzReviewNo;
	private String mzReviewTitle;
	private String mzReviewContent;
	private Date mzReviewDate;
	private String mzFileName;
	private long mzReviewFileSize;
	private String mzReviewFilePath;
	private Timestamp mzReviewFileTime;
	private String userType; // char
	private int userNo;
	private int shopNo;
	
	public MzReview() {}
	
	
	public MzReview(int mzReviewNo, String mzReviewTitle, String mzReviewContent, Date mzReviewDate, String mzFileName,
			long mzReviewFileSize, String mzReviewFilePath, Timestamp mzReviewFileTime, String userType, int userNo,
			int shopNo) {
		super();
		this.mzReviewNo = mzReviewNo;
		this.mzReviewTitle = mzReviewTitle;
		this.mzReviewContent = mzReviewContent;
		this.mzReviewDate = mzReviewDate;
		this.mzFileName = mzFileName;
		this.mzReviewFileSize = mzReviewFileSize;
		this.mzReviewFilePath = mzReviewFilePath;
		this.mzReviewFileTime = mzReviewFileTime;
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
	}




	public int getMzReviewNo() {
		return mzReviewNo;
	}
	public void setMzReviewNo(int mzReviewNo) {
		this.mzReviewNo = mzReviewNo;
	}
	public String getMzReviewTitle() {
		return mzReviewTitle;
	}
	public void setMzReviewTitle(String mzReviewTitle) {
		this.mzReviewTitle = mzReviewTitle;
	}
	public String getMzReviewContent() {
		return mzReviewContent;
	}
	public void setMzReviewContent(String mzReviewContent) {
		this.mzReviewContent = mzReviewContent;
	}
	public Date getMzReviewDate() {
		return mzReviewDate;
	}
	public void setMzReviewDate(Date mzReviewDate) {
		this.mzReviewDate = mzReviewDate;
	}
	public String getMzFileName() {
		return mzFileName;
	}
	public void setMzFileName(String mzFileName) {
		this.mzFileName = mzFileName;
	}
	public long getMzReviewFileSize() {
		return mzReviewFileSize;
	}
	public void setMzReviewFileSize(long mzReviewFileSize) {
		this.mzReviewFileSize = mzReviewFileSize;
	}
	public String getMzReviewFilePath() {
		return mzReviewFilePath;
	}
	public void setMzReviewFilePath(String mzReviewFilePath) {
		this.mzReviewFilePath = mzReviewFilePath;
	}
	public Timestamp getMzReviewFileTime() {
		return mzReviewFileTime;
	}
	public void setMzReviewFileTime(Timestamp mzReviewFileTime) {
		this.mzReviewFileTime = mzReviewFileTime;
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
		return "MzReview [mzReviewNo=" + mzReviewNo + ", mzReviewTitle=" + mzReviewTitle + ", mzReviewContent="
				+ mzReviewContent + ", mzReviewDate=" + mzReviewDate + ", mzFileName=" + mzFileName
				+ ", mzReviewFileSize=" + mzReviewFileSize + ", mzReviewFilePath=" + mzReviewFilePath
				+ ", mzReviewFileTime=" + mzReviewFileTime + ", userType=" + userType + ", userNo=" + userNo
				+ ", shopNo=" + shopNo + "]";
	}
	
	
}
