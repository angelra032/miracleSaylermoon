package com.donzzul.spring.review.domain;

import java.sql.Timestamp;

public class ReviewDreamMzAll {

	// 공통
	public char userType;
	public String userNo;
	public String ShopNo;
	// 감사후기
	public int drReviewNo;
	public String drReviewTitle;
	public String drReviewCon;
	public String drReviewDate;
	public String drReviewPub;
	// 맛집후기
	public int mzReviewNo;
	public String mzReviewTitle;
	public String mzReviewCon;
	public String mzReviewDate;
	public String mzReviewFileName;
	public long mzReviewFileSize;
	public String mzReviewFilePath;
	public Timestamp mzReviewFileTime;
	
	public ReviewDreamMzAll() {}

	public ReviewDreamMzAll(char userType, String userNo, String shopNo, int drReviewNo, String drReviewTitle,
			String drReviewCon, String drReviewDate, String drReviewPub, int mzReviewNo, String mzReviewTitle,
			String mzReviewCon, String mzReviewDate, String mzReviewFileName, long mzReviewFileSize,
			String mzReviewFilePath, Timestamp mzReviewFileTime) {
		super();
		this.userType = userType;
		this.userNo = userNo;
		ShopNo = shopNo;
		this.drReviewNo = drReviewNo;
		this.drReviewTitle = drReviewTitle;
		this.drReviewCon = drReviewCon;
		this.drReviewDate = drReviewDate;
		this.drReviewPub = drReviewPub;
		this.mzReviewNo = mzReviewNo;
		this.mzReviewTitle = mzReviewTitle;
		this.mzReviewCon = mzReviewCon;
		this.mzReviewDate = mzReviewDate;
		this.mzReviewFileName = mzReviewFileName;
		this.mzReviewFileSize = mzReviewFileSize;
		this.mzReviewFilePath = mzReviewFilePath;
		this.mzReviewFileTime = mzReviewFileTime;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getShopNo() {
		return ShopNo;
	}

	public void setShopNo(String shopNo) {
		ShopNo = shopNo;
	}

	public int getDrReviewNo() {
		return drReviewNo;
	}

	public void setDrReviewNo(int drReviewNo) {
		this.drReviewNo = drReviewNo;
	}

	public String getDrReviewTitle() {
		return drReviewTitle;
	}

	public void setDrReviewTitle(String drReviewTitle) {
		this.drReviewTitle = drReviewTitle;
	}

	public String getDrReviewCon() {
		return drReviewCon;
	}

	public void setDrReviewCon(String drReviewCon) {
		this.drReviewCon = drReviewCon;
	}

	public String getDrReviewDate() {
		return drReviewDate;
	}

	public void setDrReviewDate(String drReviewDate) {
		this.drReviewDate = drReviewDate;
	}

	public String getDrReviewPub() {
		return drReviewPub;
	}

	public void setDrReviewPub(String drReviewPub) {
		this.drReviewPub = drReviewPub;
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

	public String getMzReviewCon() {
		return mzReviewCon;
	}

	public void setMzReviewCon(String mzReviewCon) {
		this.mzReviewCon = mzReviewCon;
	}

	public String getMzReviewDate() {
		return mzReviewDate;
	}

	public void setMzReviewDate(String mzReviewDate) {
		this.mzReviewDate = mzReviewDate;
	}

	public String getMzReviewFileName() {
		return mzReviewFileName;
	}

	public void setMzReviewFileName(String mzReviewFileName) {
		this.mzReviewFileName = mzReviewFileName;
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

	@Override
	public String toString() {
		return "ReviewDreamMzAll [userType=" + userType + ", userNo=" + userNo + ", ShopNo=" + ShopNo + ", drReviewNo="
				+ drReviewNo + ", drReviewTitle=" + drReviewTitle + ", drReviewCon=" + drReviewCon + ", drReviewDate="
				+ drReviewDate + ", drReviewPub=" + drReviewPub + ", mzReviewNo=" + mzReviewNo + ", mzReviewTitle="
				+ mzReviewTitle + ", mzReviewCon=" + mzReviewCon + ", mzReviewDate=" + mzReviewDate
				+ ", mzReviewFileName=" + mzReviewFileName + ", mzReviewFileSize=" + mzReviewFileSize
				+ ", mzReviewFilePath=" + mzReviewFilePath + ", mzReviewFileTime=" + mzReviewFileTime + "]";
	}

}
