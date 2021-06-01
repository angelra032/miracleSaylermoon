package com.donzzul.spring.recommendboard.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendPhoto;
import com.donzzul.spring.recommendboard.store.RecommendBoardStore;

@Repository
public class RecommendBoardStoreLogic implements RecommendBoardStore {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<RecommendBoard> selectAllRecommend() {
		return (ArrayList)sqlSession.selectList("recommendMapper.selectAllList");
	}
	
	@Override
	public ArrayList<RecommendBoard> selectAllRecommend(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("recommendMapper.selectAllList", null, rowBounds);
	}

	@Override
	public int getListCount() {
		return sqlSession.selectOne("recommendMapper.selectListCount");
	}

	@Override
	public RecommendBoard selectOneRecommend(int recommendNo) {
		return sqlSession.selectOne("recommendMapper.selectOneDetail", recommendNo);
	}

	@Override
	public int insertRecommend(RecommendBoard recommendBoard) {
		return sqlSession.insert("recommendMapper.insertRecommend", recommendBoard);
	}

	@Override
	public int updateRecommend(RecommendBoard recommendBoard) {
		return sqlSession.update("recommendMapper.updateRecommend", recommendBoard);
	}

	@Override
	public int deleteRecommend(int recommendNo) {
		return sqlSession.delete("recommendMapper.deleteRecommend", recommendNo);
	}

	// 사진 추가
	@Override
	public int insertPhoto(RecommendPhoto recoPhoto) {
		return sqlSession.insert("recommendMapper.insertPhoto", recoPhoto);
	}
	
	// 사진 조회
	@Override
	public ArrayList<RecommendPhoto> selectPhoto(int recommendNo) {
		return (ArrayList)sqlSession.selectList("recommendMapper.selectPhoto", recommendNo);
	}

	// 수정 전 사진삭제
	@Override
	public int deleteBeforePhoto(int recommendNo) {
		return sqlSession.delete("recommendMapper.deleteBeforePhoto", recommendNo);
	}
	
	// 조회수 증가
	@Override
	public int updateCount(int recommendNo) {
		return sqlSession.update("recommendMapper.updateCount", recommendNo);
	}






}
