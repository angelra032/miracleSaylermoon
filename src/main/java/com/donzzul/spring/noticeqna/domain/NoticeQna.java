package com.donzzul.spring.noticeqna.domain;

public class NoticeQna {
	private int noticeQNaNo;
	private String noticeTitle;
	private String noticeCon;
	private String qnaTitle;
	private char boardType;
	private int partnerNo;
	private char userType;
	public int getNoticeQNaNo() {
		return noticeQNaNo;
	}
	public void setNoticeQNaNo(int noticeQNaNo) {
		this.noticeQNaNo = noticeQNaNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeCon() {
		return noticeCon;
	}
	public void setNoticeCon(String noticeCon) {
		this.noticeCon = noticeCon;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public char getBoardType() {
		return boardType;
	}
	public void setBoardType(char boardType) {
		this.boardType = boardType;
	}
	public int getPartnerNo() {
		return partnerNo;
	}
	public void setPartnerNo(int partnerNo) {
		this.partnerNo = partnerNo;
	}
	public char getUserType() {
		return userType;
	}
	public void setUserType(char userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "NoticeQna [noticeQNaNo=" + noticeQNaNo + ", noticeTitle=" + noticeTitle + ", noticeCon=" + noticeCon
				+ ", qnaTitle=" + qnaTitle + ", boardType=" + boardType + ", partnerNo=" + partnerNo + ", userType="
				+ userType + "]";
	}
	
	
}
