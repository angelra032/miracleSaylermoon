package com.donzzul.spring.noticeqna.domain;

public class NoticeQa {
	private int noticeQaNo;
	private String noticeQaTitle;
	private String noticeQaContent;
	private String boardType; // char
	private String boardPrivate; // char
	private int partnerNo;
	private String userType; // char
	
	public NoticeQa() {}

	public NoticeQa(int noticeQaNo, String noticeQaTitle, String noticeQaContent, String boardType, String boardPrivate,
			int partnerNo, String userType) {
		super();
		this.noticeQaNo = noticeQaNo;
		this.noticeQaTitle = noticeQaTitle;
		this.noticeQaContent = noticeQaContent;
		this.boardType = boardType;
		this.boardPrivate = boardPrivate;
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

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getBoardPrivate() {
		return boardPrivate;
	}

	public void setBoardPrivate(String boardPrivate) {
		this.boardPrivate = boardPrivate;
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
				+ noticeQaContent + ", boardType=" + boardType + ", boardPrivate=" + boardPrivate + ", partnerNo="
				+ partnerNo + ", userType=" + userType + "]";
	}
	
	
	
}
