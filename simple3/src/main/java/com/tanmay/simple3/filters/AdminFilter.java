package com.tanmay.simple3.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.tanmay.simple3.domain.Admin;
import com.tanmay.simple3.services.CustomUserDetailsService;


@WebFilter("/*")
public class AdminFilter implements Filter{
	
	
	  @Autowired
      private CustomUserDetailsService userService;
	  private static final Logger LOGGER = LoggerFactory.getLogger(AdminFilter.class);

	  @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }
	  
	  @Override
	    public void destroy() {
	         
	    }
	  
	  public AdminFilter() {}
	
    
	public void doFilter(ServletRequest request, ServletResponse responce,FilterChain chain) throws IOException, ServletException{
		
		
		LOGGER.info("inside do filter of admin filter");
		HttpServletRequest req = (HttpServletRequest)request;
	
		String adminEmail = request.getParameter("email");
	    
		Admin admin1 = userService.findAdminByEmail(adminEmail);

   		if (adminEmail.equals(admin1.getPassword())) {
   			chain.doFilter(request, responce);
   		} 
	}
}
