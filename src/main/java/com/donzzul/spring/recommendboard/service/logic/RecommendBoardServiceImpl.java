package com.donzzul.spring.recommendboard.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendPhoto;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;
import com.donzzul.spring.recommendboard.store.RecommendBoardStore;

@Service
public class RecommendBoardServiceImpl implements RecommendBoardService {
	
	@Autowired
	private RecommendBoardStore reStore;

	@Override
	public ArrayList<RecommendBoard> selectAllRecommend() {
		return reStore.selectAllRecommend();
	}
	

	@Override
	public ArrayList<RecommendBoard> selectAllRecommend(PageInfo pi) {
		return reStore.selectAllRecommend(pi);
	}
	
	@Override
	public int getListCount() {
		return reStore.getListCount();
	}

	@Override
	public RecommendBoard selectOneRecommend(int recommendNo) {
		return reStore.selectOneRecommend(recommendNo);
	}

	@Override
	public int insertRecommend(RecommendBoard recommendBoard) {
		return reStore.insertRecommend(recommendBoard);
	}

	// 수정
	@Override
	public int updateRecommend(RecommendBoard recommendBoard) {
		return reStore.updateRecommend(recommendBoard);
	}

	@Override
	public int deleteRecommend(int recommendNo) {
		return reStore.deleteRecommend(recommendNo);
	}

	// 사진저장
	@Override
	public int insertPhoto(RecommendPhoto recoPhoto) {
		return reStore.insertPhoto(recoPhoto);
	}

	// 사진조회
	@Override
	public ArrayList<RecommendPhoto> selectPhoto(int recommendNo) {
		return reStore.selectPhoto(recommendNo);
	}

	// 수정 전 사진삭제
	@Override
	public int deleteBeforePhoto(int recommendNo) {
		return reStore.deleteBeforePhoto(recommendNo);
	}




	

}
