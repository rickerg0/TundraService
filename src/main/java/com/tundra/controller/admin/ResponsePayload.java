package com.tundra.controller.admin;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponsePayload<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	@JsonInclude(Include.NON_NULL)
	private T payload;
	
	public ResponsePayload(String token) {
		super();
		this.token = token;
	}
	
	public ResponsePayload(String token, T payload) {
		super();
		this.token = token;
		this.payload = payload;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	
}
