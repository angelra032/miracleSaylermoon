package com.donzzul.spring.recommendboard.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.store.RecommendBoardStore;

@Repository
public class RecommendBoardStoreLogic implements RecommendBoardStore {

	@Override
	public ArrayList<RecommendBoard> selectAllRecommend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecommendBoard selectOneRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecommend(RecommendBoard recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommend(RecommendBoard recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}



}
