package com.donzzul.spring.recommendboard.service;

import java.util.ArrayList;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendBoardPage;

public interface RecommendBoardService {
	// 맛집추천 리스트 / 조회 / 등록 / 수정 / 삭제
	public ArrayList<RecommendBoard> selectAllRecommend();
	public ArrayList<RecommendBoard> selectAllRecommend(RecommendBoardPage pi);
	public RecommendBoard selectOneRecommend(int recommendNo);
	public int insertRecommend(RecommendBoard recommendBoard);
	public int updateRecommend(RecommendBoard recommendBoard);
	public int deleteRecommend(int recommendNo);
	public int getListCount();
	
}
