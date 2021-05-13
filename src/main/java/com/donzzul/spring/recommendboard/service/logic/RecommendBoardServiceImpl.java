package com.donzzul.spring.recommendboard.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;
import com.donzzul.spring.recommendboard.store.RecommendBoardStore;

@Service
public class RecommendBoardServiceImpl implements RecommendBoardService {
	
	@Autowired
	private RecommendBoardStore reStore;

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
