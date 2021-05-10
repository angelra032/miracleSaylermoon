package com.donzzul.spring.mzreview.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;
import com.donzzul.spring.mzreview.store.MzReviewStore;

@Repository
public class MzReviewStoreLogic implements MzReviewStore {

//	SqlSession
	
	@Override
	public ArrayList<MzReview> selectAllReview() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MzReview selectOneReview(int mzReviewNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMzReview(MzReview mzReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMzReview(MzReview mzReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMzReview(int mzReviewNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
    
    @Override //D 가게 전체 후기 가져오기	
    public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo) {
        return null;
    } 
    
    // selectAllReview 오버로딩 (사진 포함)
    public ArrayList<MzReview> selectAllReview(int shopNo) {
        return null;
    } 

}
