package com.donzzul.spring.mzrecommendation.service;

import java.util.ArrayList;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;

public interface MzRecommendService {
	// 맛집추천 리스트 / 조회 / 등록 / 수정 / 삭제
	public ArrayList<MzRecommendation> selectAllRecommend();
	public MzRecommendation selectOneRecommend(int recommendationNo);
	public int insertRecommend(MzRecommendation recommend);
	public int updateRecommend(MzRecommendation recommend);
	public int deleteRecommend(int recommendationNo);
	
}
