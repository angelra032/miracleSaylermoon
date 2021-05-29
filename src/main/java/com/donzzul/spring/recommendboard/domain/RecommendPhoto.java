package com.donzzul.spring.recommendboard.domain;

import java.sql.Timestamp;

public class RecommendPhoto  {
	private int recommendFileNo;
	private String recommendOriginalFileName;
	private String recommendRenameFileName;
	private String recommendFilePath;
	private long recommendFileSize;
	private Timestamp recommendFileTime;
	private int recommendNo; // 가게추천글번호
	
	public RecommendPhoto() {}

	public RecommendPhoto(int recommendFileNo, String recommendOriginalFileName, String recommendRenameFileName,
			String recommendFilePath, long recommendFileSize, Timestamp recommendFileTime, int recommendNo) {
		super();
		this.recommendFileNo = recommendFileNo;
		this.recommendOriginalFileName = recommendOriginalFileName;
		this.recommendRenameFileName = recommendRenameFileName;
		this.recommendFilePath = recommendFilePath;
		this.recommendFileSize = recommendFileSize;
		this.recommendFileTime = recommendFileTime;
		this.recommendNo = recommendNo;
	}

	public int getRecommendFileNo() {
		return recommendFileNo;
	}

	public void setRecommendFileNo(int recommendFileNo) {
		this.recommendFileNo = recommendFileNo;
	}

	public String getRecommendOriginalFileName() {
		return recommendOriginalFileName;
	}

	public void setRecommendOriginalFileName(String recommendOriginalFileName) {
		this.recommendOriginalFileName = recommendOriginalFileName;
	}

	public String getRecommendRenameFileName() {
		return recommendRenameFileName;
	}

	public void setRecommendRenameFileName(String recommendRenameFileName) {
		this.recommendRenameFileName = recommendRenameFileName;
	}

	public String getRecommendFilePath() {
		return recommendFilePath;
	}

	public void setRecommendFilePath(String recommendFilePath) {
		this.recommendFilePath = recommendFilePath;
	}

	public long getRecommendFileSize() {
		return recommendFileSize;
	}

	public void setRecommendFileSize(long recommendFileSize) {
		this.recommendFileSize = recommendFileSize;
	}

	public Timestamp getRecommendFileTime() {
		return recommendFileTime;
	}

	public void setRecommendFileTime(Timestamp recommendFileTime) {
		this.recommendFileTime = recommendFileTime;
	}

	public int getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(int recommendNo) {
		this.recommendNo = recommendNo;
	}

	@Override
	public String toString() {
		return "recommendPhoto [recommendFileNo=" + recommendFileNo + ", recommendOriginalFileName="
				+ recommendOriginalFileName + ", recommendRenameFileName=" + recommendRenameFileName
				+ ", recommendFilePath=" + recommendFilePath + ", recommendFileSize=" + recommendFileSize
				+ ", recommendFileTime=" + recommendFileTime + ", recommendNo=" + recommendNo + "]";
	}
	
	
	
}
