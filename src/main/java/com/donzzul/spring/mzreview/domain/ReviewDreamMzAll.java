package com.donzzul.spring.mzreview.domain;

import java.sql.Timestamp;

public class ReviewDreamMzAll {

	// 공통
	private String userType;
	private String userNo;
	private String shopNo;
	// 감사후기
	private int drReviewNo;
	private String drReviewTitle;
	private String drReviewCon;
	private String drReviewWriter;
	private String drReviewDate;
	private String drReviewPub;
	// 맛집후기
	private int mzReviewNo;
	private String mzReviewTitle;
	private String mzReviewCon;
	private String mzReviewWriter;
	private String mzReviewDate;
    // 맛집후기사진
    private int mzReviewFileNo;
	private String mzReviewFileName;
	private long mzReviewFileSize;
	private String mzReviewFilePath;
	private Timestamp mzReviewFileTime;
	
	public ReviewDreamMzAll() {}

	public ReviewDreamMzAll(String userType, String userNo, String shopNo, int drReviewNo, String drReviewTitle,
			String drReviewCon, String drReviewWriter, String drReviewDate, String drReviewPub, int mzReviewNo,
			String mzReviewTitle, String mzReviewCon, String mzReviewWriter, String mzReviewDate, int mzReviewFileNo,
			String mzReviewFileName, long mzReviewFileSize, String mzReviewFilePath, Timestamp mzReviewFileTime) {
		super();
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
		this.drReviewNo = drReviewNo;
		this.drReviewTitle = drReviewTitle;
		this.drReviewCon = drReviewCon;
		this.drReviewWriter = drReviewWriter;
		this.drReviewDate = drReviewDate;
		this.drReviewPub = drReviewPub;
		this.mzReviewNo = mzReviewNo;
		this.mzReviewTitle = mzReviewTitle;
		this.mzReviewCon = mzReviewCon;
		this.mzReviewWriter = mzReviewWriter;
		this.mzReviewDate = mzReviewDate;
        this.mzReviewFileNo = mzReviewFileNo;
		this.mzReviewFileName = mzReviewFileName;
		this.mzReviewFileSize = mzReviewFileSize;
		this.mzReviewFilePath = mzReviewFilePath;
		this.mzReviewFileTime = mzReviewFileTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
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

	public String getDrReviewWriter() {
		return drReviewWriter;
	}

	public void setDrReviewWriter(String drReviewWriter) {
		this.drReviewWriter = drReviewWriter;
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

	public String getMzReviewWriter() {
		return mzReviewWriter;
	}

	public void setMzReviewWriter(String mzReviewWriter) {
		this.mzReviewWriter = mzReviewWriter;
	}

	public String getMzReviewDate() {
		return mzReviewDate;
	}

	public void setMzReviewDate(String mzReviewDate) {
		this.mzReviewDate = mzReviewDate;
	}
    
    public int getMzReviewFileNo() {
		return mzReviewFileNo;
	}

	public void setMzReviewFileNo(int mzReviewFileNo) {
		this.mzReviewFileNo = mzReviewFileNo;
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
		return "ReviewDreamMzAll [userType=" + userType + ", userNo=" + userNo + ", shopNo=" + shopNo + ", drReviewNo="
				+ drReviewNo + ", drReviewTitle=" + drReviewTitle + ", drReviewCon=" + drReviewCon + ", drReviewWriter="
				+ drReviewWriter + ", drReviewDate=" + drReviewDate + ", drReviewPub=" + drReviewPub + ", mzReviewNo="
				+ mzReviewNo + ", mzReviewTitle=" + mzReviewTitle + ", mzReviewCon=" + mzReviewCon + ", mzReviewWriter="
				+ mzReviewWriter + ", mzReviewDate=" + mzReviewDate + ", mzReviewFileNo=" + mzReviewFileNo + ", mzReviewFileName=" + mzReviewFileName + ", mzReviewFileSize=" + mzReviewFileSize + ", mzReviewFilePath=" + mzReviewFilePath + ", mzReviewFileTime=" + mzReviewFileTime + "]";
	}

}
