package com.donzzul.spring.dreamreview.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.dreamreview.store.DreamReviewStore;

@Service
public class DreamReviewServiceImpl implements DreamReviewService {

	@Autowired
	private DreamReviewStore drStore;
	
	@Override
	public int getListCount() {
		return drStore.selectListCount();
	}

	@Override
	public ArrayList<DreamReview> selectAllDreamReview() {
		return drStore.selectAllDreamReview();
	}
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi) {
		return  drStore.selectAllDreamReview(pi);
	}
	
	@Override
	public DreamReview selectOneDreamReview(int drmRviewNo) {
		return drStore.selectOneDreamReview(drmRviewNo);
	}

	@Override
	public int insertDreamReview(DreamReview dreamReview) {
		int result = drStore.insertDreamReview(dreamReview);
		return result;
	}

	@Override
	public int updateDreamReview(DreamReview dreamReview) {
		return drStore.updateDreamReview(dreamReview);
	}

	@Override
	public int deleteDreamReview(int drmRviewNo) {
		return drStore.deleteDreamReview(drmRviewNo);
	}
	
    @Override
    public ArrayList<DreamReview> selectAllDreamReview(int shopNo) {
    	System.out.println("service");
        return drStore.selectAllDreamReview(shopNo);
    }

    // 꿈나무 마이 페이지
	@Override
	public ArrayList<DreamReview> drmRwUptoThree(int userNo) {
		return drStore.drmRwUptoThree(userNo);
	}

	@Override
	public int dreamGetListCount(int userNo) {
		return drStore.dreamGetListCount(userNo);
	}

	@Override
	public ArrayList<DreamReview> reviewListByDream(int userNo, PageInfo pi) {
		return drStore.reviewListByDream(userNo, pi);
	}






}
