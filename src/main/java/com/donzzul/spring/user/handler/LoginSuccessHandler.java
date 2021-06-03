package com.donzzul.spring.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.donzzul.spring.user.service.UserService;


public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private String defaultUrl;
	private String loginidname;
	private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    public LoginSuccessHandler() {}
    
	public LoginSuccessHandler(String defaultUrl, String loginidname) {
		this.defaultUrl = defaultUrl;
		this.loginidname = loginidname;
	}

	@Autowired
	private UserService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession(); 
		User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ..g_G
		com.donzzul.spring.user.domain.User loginUser = service.getUsersByID(authUser.getUsername());
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("username", authUser.getUsername());
		session.setAttribute("authorities", authentication.getAuthorities());
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            System.out.println("targetUrl :" + targetUrl);
            response.sendRedirect(targetUrl);
        } else {
        	System.out.println("defaultUrl :" + defaultUrl);
        	response.sendRedirect(defaultUrl);
        }
		
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            System.out.println("targetUrl :" + targetUrl);
            System.out.println("defaultUrl :" + defaultUrl);
            redirectStratgy.sendRedirect(request, response, targetUrl);
        } else {
            redirectStratgy.sendRedirect(request, response, defaultUrl);
        }
        
    }

	public String getLoginidname() {
        return loginidname;
    }
 
    public void setLoginidname(String loginidname) {
        this.loginidname = loginidname;
    }
 
    public String getDefaultUrl() {
        return defaultUrl;
    }
 
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }


	
	
}
