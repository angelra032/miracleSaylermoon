package com.donzzul.spring.mzreview.service;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;
import com.donzzul.spring.shop.domain.Shop;

public interface MzReviewService {
	// 리스트, 디테일, 추가, 수정, 삭제 (이후 페이징)
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(int mzReviewNo);
    public ArrayList<MzReview> selectDmReviewAll(int shopNo); // 가게 전체 후기 가져오기	
    public ArrayList<MzReview> selectAllReview(int shopNo);
    public ArrayList<MzReview> selectReviewRanking(); // 리뷰 순위
	public int getListCount();
	public ArrayList<MzReview> selectThreeReview();
}
