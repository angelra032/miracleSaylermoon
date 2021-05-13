package com.donzzul.spring.recommendboard.service;

import java.util.ArrayList;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;

public interface RecommendBoardService {
	// 맛집추천 리스트 / 조회 / 등록 / 수정 / 삭제
	public ArrayList<RecommendBoard> selectAllRecommend();
	public RecommendBoard selectOneRecommend(int recommendationNo);
	public int insertRecommend(RecommendBoard recommend);
	public int updateRecommend(RecommendBoard recommend);
	public int deleteRecommend(int recommendationNo);
	
}
