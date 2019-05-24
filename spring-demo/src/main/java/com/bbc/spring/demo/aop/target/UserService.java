package com.bbc.spring.demo.aop.target;

public interface UserService {

	void saveUser();
	
	void saveUser(String name);
	
	void updateUser();
}
