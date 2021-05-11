package com.donzzul.spring.pick.store.logic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.store.PickStore;
import com.donzzul.spring.user.domain.User;

@Repository
public class PickStoreLogic implements PickStore{

//	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertPick(Pick pick) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePick(Pick pick) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Pick> selectAllPick(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
