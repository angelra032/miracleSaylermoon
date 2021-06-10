package com.donzzul.spring.mzreview.domain;

import java.sql.Timestamp;

public class MzReviewPhoto {

	private int mzReviewFileNo;
	private String mzReviewOriginalFileName;
	private String mzReviewRenameFileName;
	private String mzReviewFilePath;
	private long mzReviewFileSize;
	private Timestamp mzReviewFileTime;
	private int mzReviewNo;
	private String userType;
	
	public MzReviewPhoto() {}

	public MzReviewPhoto(int mzReviewFileNo, String mzReviewOriginalFileName, String mzReviewRenameFileName,
			String mzReviewFilePath, long mzReviewFileSize, Timestamp mzReviewFileTime, int mzReviewNo, String userType) {
		super();
		this.mzReviewFileNo = mzReviewFileNo;
		this.mzReviewOriginalFileName = mzReviewOriginalFileName;
		this.mzReviewRenameFileName = mzReviewRenameFileName;
		this.mzReviewFilePath = mzReviewFilePath;
		this.mzReviewFileSize = mzReviewFileSize;
		this.mzReviewFileTime = mzReviewFileTime;
		this.mzReviewNo = mzReviewNo;
		this.userType = userType;
	}

	public int getMzReviewFileNo() {
		return mzReviewFileNo;
	}

	public void setMzReviewFileNo(int mzReviewFileNo) {
		this.mzReviewFileNo = mzReviewFileNo;
	}

	public String getMzReviewOriginalFileName() {
		return mzReviewOriginalFileName;
	}

	public void setMzReviewOriginalFileName(String mzReviewOriginalFileName) {
		this.mzReviewOriginalFileName = mzReviewOriginalFileName;
	}

	public String getMzReviewRenameFileName() {
		return mzReviewRenameFileName;
	}

	public void setMzReviewRenameFileName(String mzReviewRenameFileName) {
		this.mzReviewRenameFileName = mzReviewRenameFileName;
	}

	public String getMzReviewFilePath() {
		return mzReviewFilePath;
	}

	public void setMzReviewFilePath(String mzReviewFilePath) {
		this.mzReviewFilePath = mzReviewFilePath;
	}

	public long getMzReviewFileSize() {
		return mzReviewFileSize;
	}

	public void setMzReviewFileSize(long mzReviewFileSize) {
		this.mzReviewFileSize = mzReviewFileSize;
	}

	public Timestamp getMzReviewFileTime() {
		return mzReviewFileTime;
	}

	public void setMzReviewFileTime(Timestamp mzReviewFileTime) {
		this.mzReviewFileTime = mzReviewFileTime;
	}

	public int getMzReviewNo() {
		return mzReviewNo;
	}

	public void setMzReviewNo(int mzReviewNo) {
		this.mzReviewNo = mzReviewNo;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "MzReviewPhoto [mzReviewFileNo=" + mzReviewFileNo + ", mzReviewOriginalFileName="
				+ mzReviewOriginalFileName + ", mzReviewRenameFileName=" + mzReviewRenameFileName
				+ ", mzReviewFilePath=" + mzReviewFilePath + ", mzReviewFileSize=" + mzReviewFileSize
				+ ", mzReviewFileTime=" + mzReviewFileTime + ", mzReviewNo=" + mzReviewNo + ", userType=" + userType + "]";
	}
	
}
