package com.donzzul.spring.dreamreview.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.store.DreamReviewStore;

@Repository
public class DreamReviewStoreLogic implements DreamReviewStore {

	@Override
	public ArrayList<DreamReview> selectAllDreamReview() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DreamReview selectOneDreamReview(int dreamReviewNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertDreamReview(DreamReview dreamReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDreamReview(DreamReview dreamReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDreamReview(int dreamReviewNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
