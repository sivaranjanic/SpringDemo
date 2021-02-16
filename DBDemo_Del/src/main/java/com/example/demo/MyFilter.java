package com.example.demo;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyFilter implements Filter {   
	
	//   doInit() -- configure the filter -- not mandatory to provide implementation -- loaded only once
	//   doFilter() -- actual filtering logic -- called for every HttpRequest
	//   destroy()  -- destroying the filter configuration -- not mandatory to provide implementation -- loaded only once

	@Override
	public void doFilter(ServletRequest request,
						ServletResponse response,
						FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

//		if (req.getRequestURL().toString().endsWith("/getData")){
//			resp.addHeader("GET", "DATA");
//			resp.setStatus(HttpStatus.BAD_REQUEST.value());
//			resp.getOutputStream().flush();   //cleared the data
//			resp.getOutputStream().println("-- Output by filter error --");
//			chain.doFilter(req, resp);
//			return;
//		}		
		
		System.out.println("Request URI is: " + req.getRequestURI());
		chain.doFilter(req, resp);
		System.out.println("Response Status Code is: " + resp.getStatus());
	}

	@Bean
	public FilterRegistrationBean<MyFilter> filter() {
		FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
		
		bean.setFilter(new MyFilter());
		bean.addUrlPatterns("/*");  // or use setUrlPatterns()

		return bean;
	}
}