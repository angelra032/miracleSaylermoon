package com.donzzul.spring.recommendboard.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendPhoto;

public interface RecommendBoardStore {
	public ArrayList<RecommendBoard> selectAllRecommend();
	public RecommendBoard selectOneRecommend(int recommendNo);
	public int insertRecommend(RecommendBoard recommendBoard);
	public int updateRecommend(RecommendBoard recommendBoard);
	public int deleteRecommend(int recommendNo);
	public ArrayList<RecommendBoard> selectAllRecommend(PageInfo pi);
	public int getListCount();
	public int insertPhoto(RecommendPhoto recoPhoto);
}
