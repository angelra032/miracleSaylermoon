package com.donzzul.spring.recommendboard.store;

import java.util.ArrayList;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;

public interface RecommendBoardStore {
	public ArrayList<RecommendBoard> selectAllRecommend();
	public RecommendBoard selectOneRecommend(int recommendationNo);
	public int insertRecommend(RecommendBoard recommend);
	public int updateRecommend(RecommendBoard recommend);
	public int deleteRecommend(int recommendationNo);
}
