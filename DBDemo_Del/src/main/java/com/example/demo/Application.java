package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Application")  
public class Application {     // Entity class

	@Id
	private int rollNo;
	
	private String name;
	
	public Application() {
		super();
		this.rollNo = 100;
		this.name = ".Net";
	}
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString () {
		return "no: " +rollNo + " name: " +name;
	}
	
//	public void show () {
//		System.out.println( "Called show method" );
//	}
	
}
