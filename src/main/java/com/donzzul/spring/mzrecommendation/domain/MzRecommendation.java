package com.donzzul.spring.mzrecommendation.domain;

import java.sql.Date;

public class MzRecommendation {
	private int recommendationNo;
	private String recommendationTitle;
	private String recommendationContent;
	private Date recommendationDate;
	private String recommendationImg;
	private String userType;
	private int userNo;
	
	
	public MzRecommendation() {}

	public MzRecommendation(int recommendationNo, String recommendationTitle, String recommendationContent,
			Date recommendationDate, String recommendationImg, String userType, int userNo) {
		super();
		this.recommendationNo = recommendationNo;
		this.recommendationTitle = recommendationTitle;
		this.recommendationContent = recommendationContent;
		this.recommendationDate = recommendationDate;
		this.recommendationImg = recommendationImg;
		this.userType = userType;
		this.userNo = userNo;
	}

	public int getRecommendationNo() {
		return recommendationNo;
	}

	public void setRecommendationNo(int recommendationNo) {
		this.recommendationNo = recommendationNo;
	}

	public String getRecommendationTitle() {
		return recommendationTitle;
	}

	public void setRecommendationTitle(String recommendationTitle) {
		this.recommendationTitle = recommendationTitle;
	}

	public String getRecommendationContent() {
		return recommendationContent;
	}

	public void setRecommendationContent(String recommendationContent) {
		this.recommendationContent = recommendationContent;
	}

	public Date getRecommendationDate() {
		return recommendationDate;
	}

	public void setRecommendationDate(Date recommendationDate) {
		this.recommendationDate = recommendationDate;
	}

	public String getRecommendationImg() {
		return recommendationImg;
	}

	public void setRecommendationImg(String recommendationImg) {
		this.recommendationImg = recommendationImg;
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

	@Override
	public String toString() {
		return "MzRecommendation [recommendationNo=" + recommendationNo + ", recommendationTitle=" + recommendationTitle
				+ ", recommendationContent=" + recommendationContent + ", recommendationDate=" + recommendationDate
				+ ", recommendationImg=" + recommendationImg + ", userType=" + userType + ", userNo=" + userNo + "]";
	}
	
	
	
}
