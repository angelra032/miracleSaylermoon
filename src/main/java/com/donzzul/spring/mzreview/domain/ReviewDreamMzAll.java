package com.donzzul.spring.mzreview.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class ReviewDreamMzAll {

	// 공통
	private String userType;
	private String userNo;
	private String shopNo;
	// 감사후기
	private int drmReviewNo;
	private String drmReviewTitle;
	private String drmReviewContent;
	private String drmReviewWriter;
	private Date drmReviewCreateDate;
	private Date drmReviewUploadDate;
	private String drmReviewPubicYn;
	// 맛집후기
	private int mReviewNo;
	private String mReviewTitle;
	private String mReviewContent;
	private String mReviewWriter;
	private Date mReviewCreateDate;
	private Date mReviewUploadDate;
	private String mzReviewDate;
    // 맛집후기사진
    private int mReviewFileNo;
	private String mFileName;
	private long mReviewFileSize;
	private String mReviewFilePath;
	private Timestamp mReviewFileTime;
	
	public ReviewDreamMzAll() {}

	public ReviewDreamMzAll(String userType, String userNo, String shopNo, int drmReviewNo, String drmReviewTitle,
			String drmReviewContent, String drmReviewWriter, Date drmReviewCreateDate, Date drmReviewUploadDate,
			String drmReviewPubicYn, int mReviewNo, String mReviewTitle, String mReviewContent, String mReviewWriter,
			Date mReviewCreateDate, Date mReviewUploadDate, String mzReviewDate, int mReviewFileNo, String mFileName,
			long mReviewFileSize, String mReviewFilePath, Timestamp mReviewFileTime) {
		super();
		this.userType = userType;
		this.userNo = userNo;
		this.shopNo = shopNo;
		this.drmReviewNo = drmReviewNo;
		this.drmReviewTitle = drmReviewTitle;
		this.drmReviewContent = drmReviewContent;
		this.drmReviewWriter = drmReviewWriter;
		this.drmReviewCreateDate = drmReviewCreateDate;
		this.drmReviewUploadDate = drmReviewUploadDate;
		this.drmReviewPubicYn = drmReviewPubicYn;
		this.mReviewNo = mReviewNo;
		this.mReviewTitle = mReviewTitle;
		this.mReviewContent = mReviewContent;
		this.mReviewWriter = mReviewWriter;
		this.mReviewCreateDate = mReviewCreateDate;
		this.mReviewUploadDate = mReviewUploadDate;
		this.mzReviewDate = mzReviewDate;
		this.mReviewFileNo = mReviewFileNo;
		this.mFileName = mFileName;
		this.mReviewFileSize = mReviewFileSize;
		this.mReviewFilePath = mReviewFilePath;
		this.mReviewFileTime = mReviewFileTime;
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

	public int getDrmReviewNo() {
		return drmReviewNo;
	}

	public void setDrmReviewNo(int drmReviewNo) {
		this.drmReviewNo = drmReviewNo;
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

	public String getDrmReviewPubicYn() {
		return drmReviewPubicYn;
	}

	public void setDrmReviewPubicYn(String drmReviewPubicYn) {
		this.drmReviewPubicYn = drmReviewPubicYn;
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

	public String getMzReviewDate() {
		return mzReviewDate;
	}

	public void setMzReviewDate(String mzReviewDate) {
		this.mzReviewDate = mzReviewDate;
	}

	public int getmReviewFileNo() {
		return mReviewFileNo;
	}

	public void setmReviewFileNo(int mReviewFileNo) {
		this.mReviewFileNo = mReviewFileNo;
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

	@Override
	public String toString() {
		return "ReviewDreamMzAll [userType=" + userType + ", userNo=" + userNo + ", shopNo=" + shopNo + ", drmReviewNo="
				+ drmReviewNo + ", drmReviewTitle=" + drmReviewTitle + ", drmReviewContent=" + drmReviewContent
				+ ", drmReviewWriter=" + drmReviewWriter + ", drmReviewCreateDate=" + drmReviewCreateDate
				+ ", drmReviewUploadDate=" + drmReviewUploadDate + ", drmReviewPubicYn=" + drmReviewPubicYn
				+ ", mReviewNo=" + mReviewNo + ", mReviewTitle=" + mReviewTitle + ", mReviewContent=" + mReviewContent
				+ ", mReviewWriter=" + mReviewWriter + ", mReviewCreateDate=" + mReviewCreateDate
				+ ", mReviewUploadDate=" + mReviewUploadDate + ", mzReviewDate=" + mzReviewDate + ", mReviewFileNo="
				+ mReviewFileNo + ", mFileName=" + mFileName + ", mReviewFileSize=" + mReviewFileSize
				+ ", mReviewFilePath=" + mReviewFilePath + ", mReviewFileTime=" + mReviewFileTime + "]";
	}

}
