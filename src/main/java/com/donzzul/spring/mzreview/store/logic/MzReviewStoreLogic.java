package com.donzzul.spring.mzreview.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;
import com.donzzul.spring.mzreview.store.MzReviewStore;

@Repository
public class MzReviewStoreLogic implements MzReviewStore {

//	SqlSession
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<MzReview> selectAllReview() {
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectAllList");
	}
	
	@Override
	public ArrayList<MzReview> selectAllReview(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectAllList", null, rowBounds);
	}
	
	@Override
	public int getListCount() {
		return sqlSession.selectOne("mzReviewMapper.selectListCount");
	} 

	@Override
	public MzReview selectOneReview(int mzReviewNo) {
		return sqlSession.selectOne("mzReviewMapper.selectOneDetail", mzReviewNo);
	}

	@Override
	public int insertMzReview(MzReview mzReview) {
		return sqlSession.insert("mzReviewMapper.insertMzReview", mzReview);
	}

	@Override
	public int updateMzReview(MzReview mzReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMzReview(int mReviewNo) {
		return sqlSession.delete("mzReviewMapper.deleteMzReview", mReviewNo);
	}
	
    
    @Override //D 가게 전체 후기 가져오기	
    public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo) {
        return null;
    } 
    
    // selectAllReview 오버로딩 (사진 포함)
    public ArrayList<MzReview> selectAllReview(int shopNo) {
        return (ArrayList)sqlSession.selectList("mzReviewMapper.selectListShopNo", shopNo);
    }





}
