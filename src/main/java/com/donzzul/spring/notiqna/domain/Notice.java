package com.donzzul.spring.notiqna.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeCreateDate;
	private Date noticeUploadDate;
	private String userType; // char
	private String noticePublicYN;
	
	public Notice() {}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeCreateDate, Date noticeUploadDate,
			String userType, String noticePublicYN) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeUploadDate = noticeUploadDate;
		this.userType = userType;
		this.noticePublicYN = noticePublicYN;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}

	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}

	public Date getNoticeUploadDate() {
		return noticeUploadDate;
	}

	public void setNoticeUploadDate(Date noticeUploadDate) {
		this.noticeUploadDate = noticeUploadDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getNoticePublicYN() {
		return noticePublicYN;
	}

	public void setNoticePublicYN(String noticePublicYN) {
		this.noticePublicYN = noticePublicYN;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeCreateDate=" + noticeCreateDate + ", noticeUploadDate=" + noticeUploadDate + ", userType="
				+ userType + ", noticePublicYN=" + noticePublicYN + "]";
	}
	
}
