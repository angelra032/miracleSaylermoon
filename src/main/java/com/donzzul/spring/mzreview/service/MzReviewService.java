package com.donzzul.spring.mzreview.service;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;

public interface MzReviewService {
	// 리스트, 디테일, 추가, 수정, 삭제 (이후 페이징)
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(MzReview mzReview);
    public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo); // 가게 전체 후기 가져오기	
    public ArrayList<MzReview> selectAllReview(int shopNo);
	public int getListCount();
}
