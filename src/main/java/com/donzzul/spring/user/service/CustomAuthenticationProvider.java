package com.donzzul.spring.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.donzzul.spring.user.domain.CustomUserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserDetailsServiceImpl uServiceImpl;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		CustomUserDetails user = (CustomUserDetails)uServiceImpl.loadUserByUsername(username);
		
		// 비밀번호 비교
		if(!matchPassword(password, user.getPassword())) {
			throw new BadCredentialsException(username);
		}
		// 계정 활성화 여부
		if(!user.isEnabled()||user.isCredentialsNonExpired()) {
			throw new AuthenticationCredentialsNotFoundException(username);
		}

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}
	
	private boolean matchPassword(String loginPwd, String password) {
		return loginPwd.equals(password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
