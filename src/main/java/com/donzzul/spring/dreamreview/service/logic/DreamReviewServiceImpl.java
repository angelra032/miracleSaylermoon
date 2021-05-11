package com.donzzul.spring.dreamreview.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.dreamreview.store.DreamReviewStore;

@Service
public class DreamReviewServiceImpl implements DreamReviewService {

	@Autowired
	private DreamReviewStore dreamStore;
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DreamReview selectOneDreamReview(int dreamReviewNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertDreamReview(DreamReview dreamReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDreamReview(DreamReview dreamReview) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDreamReview(int dreamReviewNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
    // dreamReview 오버로딩
    public ArrayList<DreamReview> selectAllDreamReview(int shopNo) {
        return null;
    } 

}
