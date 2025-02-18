package com.donzzul.spring.mzreview.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class MzReview {
	private int mReviewNo;
	private String mReviewTitle;
	private String mReviewContent;
	private String mReviewWriter;
	private Date mReviewCreateDate;
	private Date mReviewUploadDate;
	private String mFileName;
	private long mReviewFileSize;
	private String mReviewFilePath;
	private Timestamp mReviewFileTime;
	private String userType; // char
	private int userNo;
	private int shopNo;
	private String shopName;
	private int mzReviewHit; // 조회수
	
	public MzReview() {}

	public MzReview(int mReviewNo, String mReviewTitle, String mReviewContent, String mReviewWriter,
			Date mReviewCreateDate, Date mReviewUploadDate, String mFileName, long mReviewFileSize,
			String mReviewFilePath, Timestamp mReviewFileTime, String userType, int userNo, int shopNo, String shopName,
			int mzReviewHit) {
		super();
		this.mReviewNo = mReviewNo;
		this.mReviewTitle = mReviewTitle;
		this.mReviewContent = mReviewContent;
		this.mReviewWriter = mReviewWriter;
		this.mReviewCreateDate = mReviewCreateDate;
		this.mReviewUploadDate = mReviewUploadDate;
		this.mFileName = mFileName;
		this.mReviewFileSize = mReviewFileSize;
		this.mReviewFilePath = mReviewFilePath;
		this.mReviewFileTime = mReviewFileTime;
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
		this.shopName = shopName;
		this.mzReviewHit = mzReviewHit;
	}

	public int getmReviewNo() {
		return mReviewNo;
	}

	public void setmReviewNo(int mReviewNo) {
		this.mReviewNo = mReviewNo;
	}

	public String getmReviewTitle() {
		return mReviewTitle;
	}

	public void setmReviewTitle(String mReviewTitle) {
		this.mReviewTitle = mReviewTitle;
	}

	public String getmReviewContent() {
		return mReviewContent;
	}

	public void setmReviewContent(String mReviewContent) {
		this.mReviewContent = mReviewContent;
	}

	public String getmReviewWriter() {
		return mReviewWriter;
	}

	public void setmReviewWriter(String mReviewWriter) {
		this.mReviewWriter = mReviewWriter;
	}

	public Date getmReviewCreateDate() {
		return mReviewCreateDate;
	}

	public void setmReviewCreateDate(Date mReviewCreateDate) {
		this.mReviewCreateDate = mReviewCreateDate;
	}

	public Date getmReviewUploadDate() {
		return mReviewUploadDate;
	}

	public void setmReviewUploadDate(Date mReviewUploadDate) {
		this.mReviewUploadDate = mReviewUploadDate;
	}

	public String getmFileName() {
		return mFileName;
	}

	public void setmFileName(String mFileName) {
		this.mFileName = mFileName;
	}

	public long getmReviewFileSize() {
		return mReviewFileSize;
	}

	public void setmReviewFileSize(long mReviewFileSize) {
		this.mReviewFileSize = mReviewFileSize;
	}

	public String getmReviewFilePath() {
		return mReviewFilePath;
	}

	public void setmReviewFilePath(String mReviewFilePath) {
		this.mReviewFilePath = mReviewFilePath;
	}

	public Timestamp getmReviewFileTime() {
		return mReviewFileTime;
	}

	public void setmReviewFileTime(Timestamp mReviewFileTime) {
		this.mReviewFileTime = mReviewFileTime;
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

	public int getMzReviewHit() {
		return mzReviewHit;
	}

	public void setMzReviewHit(int mzReviewHit) {
		this.mzReviewHit = mzReviewHit;
	}

	@Override
	public String toString() {
		return "MzReview [mReviewNo=" + mReviewNo + ", mReviewTitle=" + mReviewTitle + ", mReviewContent="
				+ mReviewContent + ", mReviewWriter=" + mReviewWriter + ", mReviewCreateDate=" + mReviewCreateDate
				+ ", mReviewUploadDate=" + mReviewUploadDate + ", mFileName=" + mFileName + ", mReviewFileSize="
				+ mReviewFileSize + ", mReviewFilePath=" + mReviewFilePath + ", mReviewFileTime=" + mReviewFileTime
				+ ", userType=" + userType + ", userNo=" + userNo + ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", mzReviewHit=" + mzReviewHit + "]";
	}

	
}
