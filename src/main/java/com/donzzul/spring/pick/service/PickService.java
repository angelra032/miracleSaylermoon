package com.donzzul.spring.pick.service;

import java.util.List;

import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.user.domain.User;

public interface PickService {

	public int insertPick(Pick pick); // 찜 등록
	public int deletePick(Pick pick); // 찜 해제
	public List<Pick> selectAllPick(User user); //찜 목록
}
