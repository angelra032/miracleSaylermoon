package com.donzzul.spring.mzrecommendation.domain;

import java.sql.Timestamp;

public class RecommendationPhoto {
	private int recommendationFileNo;
	private String recommendationFileName;
	private String recommendationFilePath;
	private long recommendationFileSize;
	private Timestamp recommendationFileTime;
	private int recommendationNo; // 가게추천글번호
	
	public RecommendationPhoto() {}
	
	
	public RecommendationPhoto(int recommendationFileNo, String recommendationFileName, String recommendationFilePath,
			long recommendationFileSize, Timestamp recommendationFileTime, int recommendationNo) {
		super();
		this.recommendationFileNo = recommendationFileNo;
		this.recommendationFileName = recommendationFileName;
		this.recommendationFilePath = recommendationFilePath;
		this.recommendationFileSize = recommendationFileSize;
		this.recommendationFileTime = recommendationFileTime;
		this.recommendationNo = recommendationNo;
	}

	public int getRecommendationFileNo() {
		return recommendationFileNo;
	}
	public void setRecommendationFileNo(int recommendationFileNo) {
		this.recommendationFileNo = recommendationFileNo;
	}
	public String getRecommendationFileName() {
		return recommendationFileName;
	}
	public void setRecommendationFileName(String recommendationFileName) {
		this.recommendationFileName = recommendationFileName;
	}
	public String getRecommendationFilePath() {
		return recommendationFilePath;
	}
	public void setRecommendationFilePath(String recommendationFilePath) {
		this.recommendationFilePath = recommendationFilePath;
	}
	public long getRecommendationFileSize() {
		return recommendationFileSize;
	}
	public void setRecommendationFileSize(long recommendationFileSize) {
		this.recommendationFileSize = recommendationFileSize;
	}
	public Timestamp getRecommendationFileTime() {
		return recommendationFileTime;
	}
	public void setRecommendationFileTime(Timestamp recommendationFileTime) {
		this.recommendationFileTime = recommendationFileTime;
	}
	public int getRecommendationNo() {
		return recommendationNo;
	}
	public void setRecommendationNo(int recommendationNo) {
		this.recommendationNo = recommendationNo;
	}
	@Override
	public String toString() {
		return "RecommendationPhoto [recommendationFileNo=" + recommendationFileNo + ", recommendationFileName="
				+ recommendationFileName + ", recommendationFilePath=" + recommendationFilePath
				+ ", recommendationFileSize=" + recommendationFileSize + ", recommendationFileTime="
				+ recommendationFileTime + ", recommendationNo=" + recommendationNo + "]";
	}
	
	
}
