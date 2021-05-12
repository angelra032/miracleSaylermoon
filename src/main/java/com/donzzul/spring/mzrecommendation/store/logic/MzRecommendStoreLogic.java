package com.donzzul.spring.mzrecommendation.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;
import com.donzzul.spring.mzrecommendation.store.MzRecommendStore;

@Repository
public class MzRecommendStoreLogic implements MzRecommendStore {

	@Override
	public ArrayList<MzRecommendation> selectAllRecommend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MzRecommendation selectOneRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecommend(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommend(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
