package com.donzzul.spring.user.store;

import com.donzzul.spring.user.domain.User;

public interface UserStore {
 
	public User selectOneUser(User user);
	public int checkIdDup(String userId);
	public int insertDreamUser(User user);
	public int insertMzUser(User user);
	public int insertPartnerUser(User user);
	public int updateUser(User user);
	public int deleteUser(String userId);
}
