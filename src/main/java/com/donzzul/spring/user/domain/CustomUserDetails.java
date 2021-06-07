package com.donzzul.spring.user.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/* Spring Security 로그인을 위한 UserDetails VO 객체 */
public class CustomUserDetails implements UserDetails {
	
	// 안만들어도 상관없지만 Warning이 발생함
	private static final long serialVersionUID = 1L;
	
	private String ID;
    private String PASSWORD;
    private String AUTHORITY;
    private boolean ENABLED;
    private String NAME;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
    }
 
    @Override
    public String getPassword() {
        return PASSWORD;
    }
 
    @Override
    public String getUsername() {
        return ID;
    }
 
    // 계정이 만료 되지 않았는가?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    // 계정이 잠기지 않았는가?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    // 패스워드가 만료되지 않았는가?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    // 계정이 활성화 되었는가?
    @Override
    public boolean isEnabled() {
        return ENABLED;
    }
    
    public String getNAME() {
        return NAME;
    }
 
    public void setNAME(String name) {
        NAME = name;
    }


}
