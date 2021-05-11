package com.donzzul.spring.mzrecommendation.store;

import java.util.ArrayList;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;

public interface MzRecommendationStore {
	public ArrayList<MzRecommendation> selectAllRecommendation();
	public MzRecommendation selectOneRecommendation(int recommendationNo);
	public int insertRecommendation(MzRecommendation recommend);
	public int updateRecommendation(MzRecommendation recommend);
	public int deleteRecommendation(int recommendationNo);
}
