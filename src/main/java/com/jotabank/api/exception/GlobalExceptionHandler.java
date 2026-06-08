package com.jotabank.api.exception;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {
	
	public GlobalExceptionHandler(String status, String msg) {
		setStatus(status);
		setMensagem(msg);
	}
	
	private String status;
	private String mensagem;
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	
}
