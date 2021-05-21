package com.donzzul.spring.mzreview.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;

public interface MzReviewStore {
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(int mzReviewNo);
	public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo); // 가게 전체 후기 가져오기
	public ArrayList<MzReview> selectAllReview(int shopNo);
	public int getListCount();
}
