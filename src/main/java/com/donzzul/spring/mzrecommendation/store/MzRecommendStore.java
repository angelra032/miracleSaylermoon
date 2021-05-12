package com.donzzul.spring.mzrecommendation.store;

import java.util.ArrayList;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;

public interface MzRecommendStore {
	public ArrayList<MzRecommendation> selectAllRecommend();
	public MzRecommendation selectOneRecommend(int recommendationNo);
	public int insertRecommend(MzRecommendation recommend);
	public int updateRecommend(MzRecommendation recommend);
	public int deleteRecommend(int recommendationNo);
}
