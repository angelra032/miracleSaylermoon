package com.donzzul.spring.notice.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private int userNo; // 회원번호
	private String userType; // char
	
	public Notice() {}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeDate=" + noticeDate + ", userNo=" + userNo + ", userType=" + userType + "]";
	}
	
	
}
