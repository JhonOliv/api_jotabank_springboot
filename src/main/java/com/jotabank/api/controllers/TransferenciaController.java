package com.jotabank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.dtos.DtoTransferRequest;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.exception.MessageError;
import com.jotabank.api.services.TransferenciaFactoryService;

@RestController
@RequestMapping("api/v1/transfer")
public class TransferenciaController {
	@Autowired
	private TransferenciaFactoryService serviceTransf;
	
	@PostMapping("/pix/{idConta}")
	public ResponseEntity<?> transfPix(@RequestBody DtoTransferRequest request, @PathVariable Long idConta){
		try {
				if(request.getCpf().isBlank() || request.getCpf().isEmpty()
				|| request.getCpf().matches("^[\\\\\\\\p{L}\\\\\\\\s]+$\\\\r\\\\n")) {
					MessageError error = new MessageError("Campo cpf está incorreto.", "404");
					return ResponseEntity.badRequest().body(error);
				}
				
				DtoTransferResponse response =  serviceTransf.transfePix(idConta, request.getCpf(), request.getValorTranferencia());				
				return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		
		MessageError error = new MessageError("Campo cpf está incorreto.", "404");
		return 	ResponseEntity.badRequest().body(error);

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
