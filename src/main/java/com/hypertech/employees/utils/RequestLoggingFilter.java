package com.hypertech.employees.utils;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter{@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	 HttpServletRequest httpRequest = (HttpServletRequest) request;
     
     log.info("Request Method: {} | Path: {}", httpRequest.getMethod(), httpRequest.getRequestURI());
     
     Enumeration<String> headerNames = httpRequest.getHeaderNames();
     while (headerNames.hasMoreElements()) {
         String headerName = headerNames.nextElement();
         log.info("Header: {} = {}", headerName, httpRequest.getHeader(headerName));
     }

     chain.doFilter(request, response);
 	}	
		
}
