package com.donzzul.spring.recommendboard.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;
import com.donzzul.spring.recommendboard.store.RecommendBoardStore;

@Service
public class RecommendBoardServiceImpl implements RecommendBoardService {
	
	@Autowired
	private RecommendBoardStore reStore;

	@Override
	public ArrayList<RecommendBoard> selectAllRecommend() {
		return reStore.selectAllRecommend();
	}
	

	@Override
	public ArrayList<RecommendBoard> selectAllRecommend(PageInfo pi) {
		return reStore.selectAllRecommend(pi);
	}
	
	@Override
	public int getListCount() {
		return reStore.getListCount();
	}

	@Override
	public RecommendBoard selectOneRecommend(int recommendNo) {
		return reStore.selectOneRecommend(recommendNo);
	}

	@Override
	public int insertRecommend(RecommendBoard recommendBoard) {
		int result = reStore.insertRecommend(recommendBoard);
		return result;
	}

	@Override
	public int updateRecommend(RecommendBoard recommendBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommend(int recommendNo) {
		return reStore.deleteRecommend(recommendNo);
	}




	

}
