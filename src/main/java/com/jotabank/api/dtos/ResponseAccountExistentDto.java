package com.jotabank.api.dtos;

public class ResponseAccountExistentDto {
	
	private String msg;
	private Long code;
	
	public ResponseAccountExistentDto(String msg, Long code) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
		this.code = code;
	}
	
	public String getMsg() {
		return this.msg;
	}
	public Long getCode() {
		return this.code;
	}

}
