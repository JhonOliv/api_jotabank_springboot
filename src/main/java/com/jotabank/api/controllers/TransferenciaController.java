package com.jotabank.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jotabank.api.dtos.DtoTransferRequest;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.repositories.TransferenciaRepository;
import com.jotabank.api.services.TransferenciaFactoryService;

@Controller
@RequestMapping("api/v1/transf")
public class TransferenciaController {
	
	private TransferenciaFactoryService serviceTransf;
	private TransferenciaRepository repositoryTransf;
	
	@PostMapping("/pix")
	public ResponseEntity<DtoTransferResponse> transfPix(@RequestBody DtoTransferRequest request){
		return null;
	}
	
	@PostMapping("/ted")
	public ResponseEntity<DtoTransferResponse> transfTed(@RequestBody DtoTransferRequest request){
		return null;
	}
	
	@PostMapping("/doc")
	public ResponseEntity<DtoTransferResponse> transfDoc(@RequestBody DtoTransferRequest request){
		return null;
	}
	
	@PostMapping("/saque")
	public ResponseEntity<DtoTransferResponse> transfSaque(@RequestBody DtoTransferRequest request){
		return null;
	}
	
	@PostMapping("/deposito")
	public ResponseEntity<DtoTransferResponse> transfdeposito(@RequestBody DtoTransferRequest request){
		return null;
	} 
	

}
