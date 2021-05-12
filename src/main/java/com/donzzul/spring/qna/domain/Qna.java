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
	
}
