package com.donzzul.spring.mzreview.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPhoto;
import com.donzzul.spring.shop.domain.Shop;

public interface MzReviewStore {
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public ArrayList<MzReview> selectAllReviewToMyPage(int userNo, PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(int mzReviewNo);
	public ArrayList<MzReview> selectAllMzReview(int shopNo);
	public int getListCount();
	public int getListCountToMyPage(int userNo);
	public ArrayList<MzReview> selectThreeReview();
	public ArrayList<MzReview> selectThreeReviewToMyPage(int userNo);
	public int updateHit(int mzReviewNo);
	
	public ArrayList<MzReview> selectAllMzReview(HashMap<String, Object> searchParam); // 더보기 - 가게 상세 맛집후기
	public int selectMzReviewCount(int shopNo); // 더보기 - 가게 맛집 후기 갯수 (count)
	
	// 사진
	public int insertPhoto(MzReviewPhoto mzReviewPhoto);
	public ArrayList<MzReviewPhoto> selectPhoto(int mzReviewNo);
	public int deleteBeforePhoto(int mzReviewNo);
}
