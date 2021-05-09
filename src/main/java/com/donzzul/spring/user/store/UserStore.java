package com.donzzul.spring.user.store;

import com.donzzul.spring.user.domain.User;

public interface UserStore {
 
	public User selectOneMember(User user);
	public int checkIdDup(String userId);
	public int insertMember(User user);
	public int updateMember(User user);
	public int deleteMember(String userId);
}
