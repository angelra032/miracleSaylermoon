package com.donzzul.spring.dreamreview.service;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;

public interface DreamReviewService {
	public ArrayList<DreamReview> selectAllDreamReview();
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi);
	public DreamReview selectOneDreamReview(int drmRviewNo);
	public int insertDreamReview(DreamReview dreamReview);
	public int updateDreamReview(DreamReview dreamReview);
	public int deleteDreamReview(int drmRviewNo);
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo);
	public int getListCount();
	public DreamReview selectOneDreamReview(); //메인페이지에서 한개만 불러오기(오버라이딩)
	
	// 꿈나무 마이페이지
	public ArrayList<DreamReview> drmRwUptoThree(int userNo);
	public int dreamGetListCount(int userNo);
	public ArrayList<DreamReview> reviewListByDream(int userNo, PageInfo pi);
}