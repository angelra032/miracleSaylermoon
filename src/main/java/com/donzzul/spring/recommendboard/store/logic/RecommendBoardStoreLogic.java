package com.donzzul.spring.recommendboard.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.domain.RecommendBoardPage;
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
	public ArrayList<RecommendBoard> selectAllRecommend(RecommendBoardPage pi) {
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
		int result = sqlSession.insert("recommendMapper.insertRecommend", recommendBoard);
		return result;
	}

	@Override
	public int updateRecommend(RecommendBoard recommendBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}






}
