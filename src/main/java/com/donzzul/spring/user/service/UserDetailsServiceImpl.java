package com.donzzul.spring.user.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.donzzul.spring.user.domain.CustomUserDetails;
import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.store.UserStore;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService uService;
	
	@Autowired
	UserStore uStore;
	
	// 저장소에 그 ID/PWD 가 있는지 확인하는 소스
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		CustomUserDetails user = uService.getUsersByID(userId);
		if (user == null) {
			throw new UsernameNotFoundException("회원없다" + user.getUsername());     // 데이터 없을때 예외처리                                                                                 
		}

		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
	    roles.add(new SimpleGrantedAuthority("ROLE_USER"));	// 로그인 성공시 ROLE_USER 롤 부여
	    UserDetails uOne = new org.springframework.security.core.userdetails.User(userId, user.getPassword(), roles); // UserDetails에 아이디, 비번, 롤, 넣어서 리턴
		
	    return uOne; // 리턴된 객체를 가지고 스프링 내부에서 인증에 대한 판단을 한다.
	}

}
