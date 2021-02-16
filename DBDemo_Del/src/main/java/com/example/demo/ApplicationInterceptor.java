package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
	
	
	// before processing the actual handler (controller) code	
   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
      
      System.out.println(" [preHandle] : " + request.getRequestURI());
      
      HandlerMethod methodHandler = (HandlerMethod) handler;
      System.out.println("handler method is: " +methodHandler.getMethod());
      System.out.println("handler method param is: " +methodHandler.getMethodParameters());
      
      return true;
   }
   
   
   // Called after invoking the handler, but before the DispatcherServlet renders the view
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler, ModelAndView modelAndView) throws Exception {
      System.out.println("[postHandle]: " + modelAndView.getViewName());
   }
   
   
   // Callback after completion of request processing, that is, after rendering the view.
   @Override
   public void afterCompletion
      (HttpServletRequest request, HttpServletResponse response, Object 
      handler, Exception exception) throws Exception {
      
      System.out.println("[afterCompletion][" + request + "][exception: " + exception + "]");
   }
}