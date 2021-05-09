package com.donzzul.spring.mzrecommendation.service;

import java.util.ArrayList;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;

public interface MzRecommendationService {
	// 맛집추천 리스트 / 조회 / 등록 / 수정 / 삭제
	public ArrayList<MzRecommendation> selectAllRecommendation();
	public MzRecommendation selectOneRecommendation(int recommendationNo);
	public int insertRecommendation(MzRecommendation recommend);
	public int updateRecommendation(MzRecommendation recommend);
	public int deleteRecommendation(MzRecommendation recommend);
	
}
