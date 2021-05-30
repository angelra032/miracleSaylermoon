package com.donzzul.spring.dreamreview.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;

public interface DreamReviewStore {
	public ArrayList<DreamReview> selectAllDreamReview();
	public DreamReview selectOneDreamReview(int drmRviewNo);
	public int insertDreamReview(DreamReview dreamReview);
	public int updateDreamReview(DreamReview dreamReview);
	public int deleteDreamReview(int drmRviewNo);
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi);
	public int selectListCount();
	ArrayList<DreamReview> selectAllDreamReview(int shopNo);
	public DreamReview selectOneDreamReview();
	public ArrayList<MzReview> selectDMReviewAll(int shopNo); // 가게 전체 후기 가져오기
	public ArrayList<Integer> selectReviewRanking(); // 가게 테마 리뷰 순위

	// 꿈나무 마이페이지
	public ArrayList<DreamReview> drmRwUptoThree(int userNo);
	public int dreamGetListCount(int userNo);
	public ArrayList<DreamReview> reviewListByDream(int userNo, PageInfo pi);
	
}
