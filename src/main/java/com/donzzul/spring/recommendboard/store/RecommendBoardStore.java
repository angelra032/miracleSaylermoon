package com.donzzul.spring.recommendboard.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendPhoto;

public interface RecommendBoardStore {
	public ArrayList<RecommendBoard> selectAllRecommend();
	public ArrayList<RecommendBoard> selectThreeRecommendToMyPage(int userNo);
	public ArrayList<RecommendBoard> selectAllRecommendToMyPage(int userNo, PageInfo pi);
	public RecommendBoard selectOneRecommend(int recommendNo);
	public int insertRecommend(RecommendBoard recommendBoard);
	public int updateRecommend(RecommendBoard recommendBoard);
	public int deleteRecommend(int recommendNo);
	public ArrayList<RecommendBoard> selectAllRecommend(PageInfo pi);
	public int getListCount(); // 
	
	public ArrayList<RecommendPhoto> selectPhoto(int recommendNo);
	public int insertPhoto(RecommendPhoto recoPhoto);
	public int deleteBeforePhoto(int recommendNo);
	public int updateHit(int recommendNo);
	public int getListCountToMyPage(int userNo);
}
