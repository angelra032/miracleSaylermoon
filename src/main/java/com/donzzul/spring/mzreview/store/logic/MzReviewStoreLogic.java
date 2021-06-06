package com.donzzul.spring.mzreview.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPhoto;
import com.donzzul.spring.mzreview.store.MzReviewStore;
import com.donzzul.spring.shop.domain.Shop;

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
		return sqlSession.update("mzReviewMapper.updateMzReview", mzReview);
	}

	@Override
	public int deleteMzReview(int mReviewNo) {
		return sqlSession.delete("mzReviewMapper.deleteMzReview", mReviewNo);
	}
	
    
    @Override //D 맛집 후기 사진 포함 전체 가져오기
    public ArrayList<MzReview> selectAllMzReview(int shopNo) {
        return (ArrayList)sqlSession.selectList("mzReviewMapper.selectListShopNo", shopNo);
    }

    // 메인페이지에 최근글 세개 뿌리기
	@Override
	public ArrayList<MzReview> selectThreeReview() {
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectThreeReview");
	}
	// 마이페이지에 최근글 세개 뿌리기
	@Override
	public ArrayList<MzReview> selectThreeReviewToMyPage(int userNo) {
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectThreeReviewToMyPage", userNo);
	}

	@Override
	public int insertPhoto(MzReviewPhoto mzReviewPhoto) {
		return sqlSession.insert("mzReviewMapper.insertMZPhoto", mzReviewPhoto);
	}

	@Override
	public ArrayList<MzReviewPhoto> selectPhoto(int mzReviewNo) {
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectMZPhoto", mzReviewNo);
	}

	@Override
	public int deleteBeforePhoto(int mzReviewNo) {
		return sqlSession.delete("mzReviewMapper.deleteBeforeMZPhoto", mzReviewNo);
	}

	
	// 더보기 - 가게 상세 맛집후기
	@Override
	public ArrayList<MzReview> selectAllMzReview(HashMap<String, Object> searchParam) {
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectMoreMzReview", searchParam);
	}

	// 더보기 - 가게 상세 맛집후기 갯수
	@Override
	public int selectMzReviewCount(int shopNo) {
		return sqlSession.selectOne("mzReviewMapper.selectMzReviewCount", shopNo);
	}

	@Override
	public int updateHit(int mzReviewNo) {
		return sqlSession.update("mzReviewMapper.updateHit", mzReviewNo);
	}

	// mz마이페이지에 전체목록 출력 
	@Override
	public ArrayList<MzReview> selectAllReviewToMyPage(int userNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("mzReviewMapper.selectAllReviewToMyPage", userNo, rowBounds);
	}

	// mz마이페이지 리스트 페이징
	@Override
	public int getListCountToMyPage(int userNo) {
		return sqlSession.selectOne("mzReviewMapper.selectListCountToMyPage", userNo);
	}

}
