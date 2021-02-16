package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // similar to @Configuration
public class ApplicationExceptionController {
	
	{
		System.out.println("controller advice class");
	}
	
   @ExceptionHandler(value = {ApplicationNotFoundException.class})
   public ResponseEntity<Object> exception(ApplicationNotFoundException applicationException) {
	   System.out.println("ApplicationNotFoundException handler code in controller advice");
      return new ResponseEntity<>(applicationException.getMessage(), HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = {IllegalArgumentException.class})
   public ResponseEntity<Object> exception(IllegalArgumentException illegalArgumentException) {
	   System.out.println("IllegalArgumentException handler code in controller advice");
      return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler
   public ResponseEntity<Object> exceptionDefault (Exception exception) {
	   System.out.println("common @exception handler code in controller advice" +exception);
      return new ResponseEntity<>("Default handler", HttpStatus.BAD_REQUEST);
   }
   
}
