package com.donzzul.spring.dreamreview.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.store.DreamReviewStore;

@Repository
public class DreamReviewStoreLogic implements DreamReviewStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectListCount() {
		return sqlSession.selectOne("drmReviewMapper.selectListCount");
	}
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview() {
		return (ArrayList)sqlSession.selectList("drmReviewMapper.selectAllList");
	}
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("drmReviewMapper.selectAllList", null, rowBounds);
	}

	@Override
	public DreamReview selectOneDreamReview(int drmRviewNo) {
		return sqlSession.selectOne("drmReviewMapper.selectOneDetail", drmRviewNo);
	}

	@Override
	public int insertDreamReview(DreamReview dreamReview) {
		return sqlSession.insert("drmReviewMapper.insertDrmReview", dreamReview);
	}

	@Override
	public int updateDreamReview(DreamReview dreamReview) {
		return sqlSession.update("drmReviewMapper.updateDrmReview", dreamReview);
	}

	@Override
	public int deleteDreamReview(int drmRviewNo) {
		return sqlSession.delete("drmReviewMapper.deleteDrmReview", drmRviewNo);
	}

	@Override
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo) {
		return (ArrayList)sqlSession.selectList("drmReviewMapper.selectListShopNo", shopNo);
	}






}
