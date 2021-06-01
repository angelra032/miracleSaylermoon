package com.donzzul.spring.recommendboard.domain;

import java.sql.Date;

public class RecommendBoard {
	private int recommendNo;
	private String recommendTitle;
	private String recommendContent;
	private String recommendWriter;
	private Date recommendCreateDate;
	private Date recommendUploadDate;
	private String recommendImg;
	private String userType;
	private int userNo;
	private int recommendCount; // 조회수
	
	
	public RecommendBoard() {}


	public RecommendBoard(int recommendNo, String recommendTitle, String recommendContent, String recommendWriter,
			Date recommendCreateDate, Date recommendUploadDate, String recommendImg, String userType, int userNo,
			int recommendCount) {
		super();
		this.recommendNo = recommendNo;
		this.recommendTitle = recommendTitle;
		this.recommendContent = recommendContent;
		this.recommendWriter = recommendWriter;
		this.recommendCreateDate = recommendCreateDate;
		this.recommendUploadDate = recommendUploadDate;
		this.recommendImg = recommendImg;
		this.userType = userType;
		this.userNo = userNo;
		this.recommendCount = recommendCount;
	}


	public int getRecommendNo() {
		return recommendNo;
	}


	public void setRecommendNo(int recommendNo) {
		this.recommendNo = recommendNo;
	}


	public String getRecommendTitle() {
		return recommendTitle;
	}


	public void setRecommendTitle(String recommendTitle) {
		this.recommendTitle = recommendTitle;
	}


	public String getRecommendContent() {
		return recommendContent;
	}


	public void setRecommendContent(String recommendContent) {
		this.recommendContent = recommendContent;
	}


	public String getRecommendWriter() {
		return recommendWriter;
	}


	public void setRecommendWriter(String recommendWriter) {
		this.recommendWriter = recommendWriter;
	}


	public Date getRecommendCreateDate() {
		return recommendCreateDate;
	}


	public void setRecommendCreateDate(Date recommendCreateDate) {
		this.recommendCreateDate = recommendCreateDate;
	}


	public Date getRecommendUploadDate() {
		return recommendUploadDate;
	}


	public void setRecommendUploadDate(Date recommendUploadDate) {
		this.recommendUploadDate = recommendUploadDate;
	}


	public String getRecommendImg() {
		return recommendImg;
	}


	public void setRecommendImg(String recommendImg) {
		this.recommendImg = recommendImg;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public int getRecommendCount() {
		return recommendCount;
	}


	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}


	@Override
	public String toString() {
		return "RecommendBoard [recommendNo=" + recommendNo + ", recommendTitle=" + recommendTitle
				+ ", recommendContent=" + recommendContent + ", recommendWriter=" + recommendWriter
				+ ", recommendCreateDate=" + recommendCreateDate + ", recommendUploadDate=" + recommendUploadDate
				+ ", recommendImg=" + recommendImg + ", userType=" + userType + ", userNo=" + userNo
				+ ", recommendCount=" + recommendCount + "]";
	}

	
}
