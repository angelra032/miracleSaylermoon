package com.donzzul.spring.mzreview.store;

import java.util.ArrayList;

import com.donzzul.spring.mzreview.domain.MzReview;

public interface MzReviewStore {
	public ArrayList<MzReview> selectAllReview();
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(MzReview mzReview);
}
