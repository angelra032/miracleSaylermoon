package com.donzzul.spring.dreamreview.store;

import java.util.ArrayList;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.domain.DreamReviewPage;

public interface DreamReviewStore {
	public ArrayList<DreamReview> selectAllDreamReview();
	public ArrayList<DreamReview> selectAllDreamReview(DreamReviewPage pi);
	public DreamReview selectOneDreamReview(int drmRviewNo);
	public int insertDreamReview(DreamReview dreamReview);
	public int updateDreamReview(DreamReview dreamReview);
	public int deleteDreamReview(int drmRviewNo);
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo);
	public int selectListCount();
}
