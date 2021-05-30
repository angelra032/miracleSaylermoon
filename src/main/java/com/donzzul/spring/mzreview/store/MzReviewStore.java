package com.donzzul.spring.mzreview.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.Shop;

public interface MzReviewStore {
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(int mzReviewNo);
	public ArrayList<MzReview> selectAllMzReview(int shopNo);
	public int getListCount();
	public ArrayList<MzReview> selectThreeReview();
}
