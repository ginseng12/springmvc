package com.testfan.spring;

import org.springframework.web.bind.annotation.RequestParam;

public class UserBean {
	
	public String name;
	public String pass;
	
	public UserBean() {
		
	}
	
	public UserBean(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
