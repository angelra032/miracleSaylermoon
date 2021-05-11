package com.donzzul.spring.qna.domain;

import java.sql.Date;

public class Qna {
	private int qaNo;
	private String qaTitle;
	private String qaContent;
	private String qaWriter;
	private Date qaDate;
	private String boardPublicYn; // char
	private int userNo; // char
	private String userType;
	private int originNo;
	private int groupOrder;
	
	public Qna() {}

	public Qna(int qaNo, String qaTitle, String qaContent, String qaWriter, Date qaDate, String boardPublicYn,
			int userNo, String userType, int originNo, int groupOrder) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.qaWriter = qaWriter;
		this.qaDate = qaDate;
		this.boardPublicYn = boardPublicYn;
		this.userNo = userNo;
		this.userType = userType;
		this.originNo = originNo;
		this.groupOrder = groupOrder;
	}

	public int getQaNo() {
		return qaNo;
	}

	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}

	public String getQaTitle() {
		return qaTitle;
	}

	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}

	public String getQaContent() {
		return qaContent;
	}

	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}

	public String getQaWriter() {
		return qaWriter;
	}

	public void setQaWriter(String qaWriter) {
		this.qaWriter = qaWriter;
	}

	public Date getQaDate() {
		return qaDate;
	}

	public void setQaDate(Date qaDate) {
		this.qaDate = qaDate;
	}

	public String getBoardPublicYn() {
		return boardPublicYn;
	}

	public void setBoardPublicYn(String boardPublicYn) {
		this.boardPublicYn = boardPublicYn;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}

	public int getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}

	@Override
	public String toString() {
		return "Qna [qaNo=" + qaNo + ", qaTitle=" + qaTitle + ", qaContent=" + qaContent + ", qaWriter=" + qaWriter
				+ ", qaDate=" + qaDate + ", boardPublicYn=" + boardPublicYn + ", userNo=" + userNo + ", userType="
				+ userType + ", originNo=" + originNo + ", groupOrder=" + groupOrder + "]";
	}
	
	
	
}
