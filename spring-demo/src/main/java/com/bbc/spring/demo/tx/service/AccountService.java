package com.bbc.spring.demo.tx.service;

public interface AccountService {

	void transfer(String from, String to, double money);
}
