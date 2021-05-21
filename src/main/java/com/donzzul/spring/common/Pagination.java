package com.donzzul.spring.common;

public class Pagination {

	public static PageInfo getPageInfo(int currentPage, int listCount) {
		PageInfo pi = null;
		int pageLimit = 5; // 한 페이지에서 보여줄 네비게이션 수
		int boardLimit = 10; // 한 페이지에서 보여줄 게시글 갯수
		int maxPage; // 전체 페이지 중 가장 마지막 페이지
		int startPage; // 현재 페이지에서 시작하는 첫번째 페이지
		int endPage; // 현재 페이지에서 끝나는 마지막 페이지
		
		// 일반적인 페이지 계산법
		maxPage = (int)((double)listCount/boardLimit + 0.9); // 계산 결과가 0.1 일 때 int 로 강제 형변환하면 0 이 되므로 이를 방지하기 위해 0.9 를 더해줌
		startPage = (((int)((double)currentPage/pageLimit + 0.9)) - 1) * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		// 오류방지용
		// endPage 가 maxPage 보다 크면 안되므로, endPage 값을 maxPage 와 같게 만들어 정상적으로 값을 리턴할 수 있도록 한다.
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		pi = new PageInfo(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
		
		return pi;
	}
}
