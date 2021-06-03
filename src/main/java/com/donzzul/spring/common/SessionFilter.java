package com.donzzul.spring.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.donzzul.spring.user.domain.User;

/**
 * Servlet Filter implementation class SessionFilter
 */
@Component
@WebFilter("/login.dz")
public class SessionFilter extends GenericFilterBean {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!principal.getClass().getSimpleName().equals("String")) {
                HttpServletRequest req = (HttpServletRequest) request;
                
                HttpSession session = req.getSession();

                User user = (User) principal;
                
                Object isUser = session.getAttribute("user");

                if (isUser == null) {
                	
                    req.getSession().setAttribute("user", user);
                    
                } else {
                	User oldUser = (User) isUser;
                    if (oldUser.getUserId() != user.getUserId()) {
                        req.getSession().setAttribute("user", user);
                    }
                }
            }
        }

        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */

}
