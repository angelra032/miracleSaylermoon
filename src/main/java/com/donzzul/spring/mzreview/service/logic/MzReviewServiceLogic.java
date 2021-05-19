package com.donzzul.spring.mzreview.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPage;
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.mzreview.store.MzReviewStore;

@Service
public class MzReviewServiceLogic implements MzReviewService {
	@Autowired
	private MzReviewStore mStore;

	@Override
	public ArrayList<MzReview> selectAllReview() {
		return mStore.selectAllReview();
	}
	
	@Override
	public ArrayList<MzReview> selectAllReview(MzReviewPage pi) {
		return mStore.selectAllReview(pi);
	}
	
	@Override
	public int getListCount() {
		return mStore.getListCount();
	} 

	@Override
	public MzReview selectOneReview(int mzReviewNo) {
		return mStore.selectOneReview(mzReviewNo);
	}

	@Override
	public int insertMzReview(MzReview mzReview) {
		return mStore.insertMzReview(mzReview);
	}

	@Override
	public int updateMzReview(MzReview mzReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMzReview(MzReview mzReview) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    @Override //D 가게 전체 후기 가져오기
    public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo) {
        return null;
    } 	
    //D selectAllReview 오버로딩 (사진 포함)
    public ArrayList<MzReview> selectAllReview(int shopNo) {
        return null;
    }






	
}
