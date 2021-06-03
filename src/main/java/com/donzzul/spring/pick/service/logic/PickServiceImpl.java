package com.donzzul.spring.pick.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.service.PickService;
import com.donzzul.spring.pick.store.PickStore;
import com.donzzul.spring.user.domain.User;

@Repository
public class PickServiceImpl implements PickService {

	@Autowired
	private PickStore store;
	
	@Override
	public int insertPick(HashMap<String, Integer> hash) {
		return store.insertPick(hash);
	}

	@Override
	public int deletePick(int pickNo) {
		return store.deletePick(pickNo);
	}
	
	@Override
	public Pick checkPick(HashMap<String, Integer> pickParam) {
		return store.checkPick(pickParam);
	}

	@Override
	public List<Pick> selectAllPick(int userNo, PageInfo pi) {
		return store.selectAllPick(userNo, pi);
	}

	// 드림 마이페이지
	@Override
	public ArrayList<Pick> dreamPickUpToThree(int userNo) {
		ArrayList<Pick> pList = store.dreamPickUpToThree(userNo);
		return pList;
	}

	@Override
	public ArrayList<Pick> pickListByDream(int userNo, PageInfo pi) {
		ArrayList<Pick> pList = store.pickListByDream(userNo, pi);
		return pList;
	}

	@Override
	public int pickListCount(int userNo) {
		return store.pickListCount(userNo);
	}

	// 마이페이지 가고싶다 삭제후 목록 재출력
	@Override
	public List<Pick> deleteAndSelectPick(int pickNo, int userNo, PageInfo pi) {
		int deleteResult = store.deletePick(pickNo);
		List<Pick> result = null;
		if(deleteResult>0) {
			result = store.selectAllPick(userNo, pi);
		}
		return result;
	}


}
