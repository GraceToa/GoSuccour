package com.gosuccour.entity;

import java.util.UUID;

public class Token {

	private String uuid;
	

	public Token() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}
	
	
}
