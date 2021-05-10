package com.donzzul.spring.qna.domain;

public class QnaReply {
	private int qaReplyNo;
	private String qaReplySubtitle;
	private String qaReplyContent;
	private String userType; // char
	private int qaNo; // 공지문의글번호
	
	public QnaReply() {}

	public QnaReply(int qaReplyNo, String qaReplySubtitle, String qaReplyContent, String userType, int qaNo) {
		super();
		this.qaReplyNo = qaReplyNo;
		this.qaReplySubtitle = qaReplySubtitle;
		this.qaReplyContent = qaReplyContent;
		this.userType = userType;
		this.qaNo = qaNo;
	}

	public int getQaReplyNo() {
		return qaReplyNo;
	}

	public void setQaReplyNo(int qaReplyNo) {
		this.qaReplyNo = qaReplyNo;
	}

	public String getQaReplySubtitle() {
		return qaReplySubtitle;
	}

	public void setQaReplySubtitle(String qaReplySubtitle) {
		this.qaReplySubtitle = qaReplySubtitle;
	}

	public String getQaReplyContent() {
		return qaReplyContent;
	}

	public void setQaReplyContent(String qaReplyContent) {
		this.qaReplyContent = qaReplyContent;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getQaNo() {
		return qaNo;
	}

	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}

	@Override
	public String toString() {
		return "QnaReply [qaReplyNo=" + qaReplyNo + ", qaReplySubtitle=" + qaReplySubtitle + ", qaReplyContent="
				+ qaReplyContent + ", userType=" + userType + ", qaNo=" + qaNo + "]";
	}

	
	
}
