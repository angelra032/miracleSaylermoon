package com.donzzul.spring.mzreview.service;

import java.util.ArrayList;

import com.donzzul.spring.mzreview.domain.MzReview;

public interface MzReviewService {
	// 리스트, 디테일, 추가, 수정, 삭제 (이후 페이징)
	public ArrayList<MzReview> selectAllReview();
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(MzReview mzReview);
}
