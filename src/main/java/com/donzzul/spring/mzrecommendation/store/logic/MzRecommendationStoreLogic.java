package com.donzzul.spring.mzrecommendation.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;
import com.donzzul.spring.mzrecommendation.store.MzRecommendationStore;

@Repository
public class MzRecommendationStoreLogic implements MzRecommendationStore {

	@Override
	public ArrayList<MzRecommendation> selectAllRecommendation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MzRecommendation selectOneRecommendation(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecommendation(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommendation(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommendation(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
