package com.donzzul.spring.pick.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public int deletePick(HashMap<String, Integer> hash) {
		return store.deletePick(hash);
	}

	@Override
	public List<Pick> selectAllPick(User user) {
		// TODO Auto-generated method stub
		return null;
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

}
