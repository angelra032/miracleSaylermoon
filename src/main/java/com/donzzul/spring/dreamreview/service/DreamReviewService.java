package com.donzzul.spring.dreamreview.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.Shop;

public interface DreamReviewService {
	public ArrayList<DreamReview> selectAllDreamReview();
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi);
	public DreamReview selectOneDreamReview(int drmRviewNo);
	public int insertDreamReview(DreamReview dreamReview);
	public int updateDreamReview(DreamReview dreamReview);
	public int deleteDreamReview(int drmReviewNo);
	public int getListCount();
	public DreamReview selectOneDreamReview(); //메인페이지에서 한개만 불러오기(오버라이딩)
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo);
	public ArrayList<DreamReview> selectDMReviewOne(ArrayList<Shop> themeList); // 가게 후기 한개 가져오기
	public ArrayList<Integer> selectReviewRanking(); // 가게 테마 리뷰 순위
	public int updateHit(int drmReviewNo);
	
	public ArrayList<DreamReview> selectDMReviewAll(HashMap<String, Object> searchParam); // 더보기 - 가게 전체 후기 가져오기
	public ArrayList<DreamReview> selectAllDreamReview(HashMap<String, Object> searchParam); // 더보기 - 가게 감사 후기
	public int selectDMReviewCount(int shopNo); // 더보기 - 가게 전체 후기 갯수 (count)
	public int selectDreamReviewCount(int shopNo); // 더보기 - 가게 감사 후기 갯수 (count)
	
	// 꿈나무 마이페이지
	public ArrayList<DreamReview> drmRwUptoThree(int userNo);
	public int dreamGetListCount(int userNo);
	public ArrayList<DreamReview> reviewListByDream(int userNo, PageInfo pi);
	public ArrayList<DreamReview> deleteAndSelectPick(int drmRviewNo, int userNo, PageInfo pi);
}