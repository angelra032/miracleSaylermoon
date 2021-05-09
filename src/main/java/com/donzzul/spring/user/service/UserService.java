package com.donzzul.spring.user.service;

import com.donzzul.spring.user.domain.User;

public interface UserService {
 
	public User loginMember(User user);
	public int checkIdDup(String userId);
	public int registerMember(User user);
	public int modifyMember(User user);
	public int deleteMember(String userId);
}
