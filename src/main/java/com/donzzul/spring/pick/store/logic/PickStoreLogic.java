package com.donzzul.spring.pick.store.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.store.PickStore;
import com.donzzul.spring.user.domain.User;

@Repository
public class PickStoreLogic implements PickStore{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertPick(HashMap<String, Integer> hash) {
		return sqlSession.insert("pickMapper.insertPick", hash);
	}

	@Override
	public int deletePick(HashMap<String, Integer> hash) {
		return sqlSession.delete("pickMapper.deletePick", hash);
	}

	@Override
	public List<Pick> selectAllPick(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
