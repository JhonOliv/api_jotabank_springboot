package com.jotabank.api.exception;

public class MessageSuccess {
	
	private String msg;
	private String code;
	
	public MessageSuccess(String msg, String code) {
		setMsg(msg);
		setCode(code);
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
