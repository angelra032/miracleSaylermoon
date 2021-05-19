package com.donzzul.spring.recommendboard.store;

import java.util.ArrayList;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendBoardPage;

public interface RecommendBoardStore {
	public ArrayList<RecommendBoard> selectAllRecommend();
	public RecommendBoard selectOneRecommend(int recommendNo);
	public int insertRecommend(RecommendBoard recommendBoard);
	public int updateRecommend(RecommendBoard recommendBoard);
	public int deleteRecommend(int recommendNo);
	public ArrayList<RecommendBoard> selectAllRecommend(RecommendBoardPage pi);
	public int getListCount();
}
