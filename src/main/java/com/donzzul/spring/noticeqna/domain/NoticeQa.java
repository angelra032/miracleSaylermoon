package com.donzzul.spring.noticeqna.domain;

import java.sql.Date;

public class NoticeQa {
	private int noticeQaNo;
	private String noticeQaTitle;
	private String noticeQaContent;
	private Date notiQaDate;
	private String boardTypeNq; // char
	private String boardPublicYn; // char
	private int partnerNo; // 회원번호
	private String userType; // char
	
	public NoticeQa() {}

	public NoticeQa(int noticeQaNo, String noticeQaTitle, String noticeQaContent, Date notiQaDate, String boardTypeNq,
			String boardPublicYn, int partnerNo, String userType) {
		super();
		this.noticeQaNo = noticeQaNo;
		this.noticeQaTitle = noticeQaTitle;
		this.noticeQaContent = noticeQaContent;
		this.notiQaDate = notiQaDate;
		this.boardTypeNq = boardTypeNq;
		this.boardPublicYn = boardPublicYn;
		this.partnerNo = partnerNo;
		this.userType = userType;
	}

	public int getNoticeQaNo() {
		return noticeQaNo;
	}

	public void setNoticeQaNo(int noticeQaNo) {
		this.noticeQaNo = noticeQaNo;
	}

	public String getNoticeQaTitle() {
		return noticeQaTitle;
	}

	public void setNoticeQaTitle(String noticeQaTitle) {
		this.noticeQaTitle = noticeQaTitle;
	}

	public String getNoticeQaContent() {
		return noticeQaContent;
	}

	public void setNoticeQaContent(String noticeQaContent) {
		this.noticeQaContent = noticeQaContent;
	}

	public Date getNotiQaDate() {
		return notiQaDate;
	}

	public void setNotiQaDate(Date notiQaDate) {
		this.notiQaDate = notiQaDate;
	}

	public String getBoardTypeNq() {
		return boardTypeNq;
	}

	public void setBoardTypeNq(String boardTypeNq) {
		this.boardTypeNq = boardTypeNq;
	}

	public String getBoardPublicYn() {
		return boardPublicYn;
	}

	public void setBoardPublicYn(String boardPublicYn) {
		this.boardPublicYn = boardPublicYn;
	}

	public int getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(int partnerNo) {
		this.partnerNo = partnerNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "NoticeQa [noticeQaNo=" + noticeQaNo + ", noticeQaTitle=" + noticeQaTitle + ", noticeQaContent="
				+ noticeQaContent + ", notiQaDate=" + notiQaDate + ", boardTypeNq=" + boardTypeNq + ", boardPublicYn="
				+ boardPublicYn + ", partnerNo=" + partnerNo + ", userType=" + userType + "]";
	}

	
	
	
}
