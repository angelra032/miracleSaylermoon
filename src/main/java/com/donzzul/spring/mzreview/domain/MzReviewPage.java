package com.donzzul.spring.mzreview.domain;

import java.util.ArrayList;

public class MzReviewPage {
	private int currentPage; // 현재페이지
	private int boardLimit; // 게시글수
	private int pageLimit; // 네비게이션 수
	private int startPage; // 네비 첫번째
	private int endPage; // 네비 마지막
	private int listCount; // 전체 게시글 갯수
	private int maxPage; // 페이지 마지막번호
	
	public MzReviewPage() {}

	public MzReviewPage(int currentPage, int boardLimit, int pageLimit, int startPage, int endPage, int listCount,
			int maxPage) {
		super();
		this.currentPage = currentPage;
		this.boardLimit = boardLimit;
		this.pageLimit = pageLimit;
		this.startPage = startPage;
		this.endPage = endPage;
		this.listCount = listCount;
		this.maxPage = maxPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "MzReviewPage [currentPage=" + currentPage + ", boardLimit=" + boardLimit + ", pageLimit=" + pageLimit
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", listCount=" + listCount + ", maxPage="
				+ maxPage + "]";
	}
	
	
	
}
