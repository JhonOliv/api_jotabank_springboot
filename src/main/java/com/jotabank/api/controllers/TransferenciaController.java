package com.jotabank.api.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.dtos.DtoSaqueRequest;
import com.jotabank.api.dtos.DtoTransferRequest;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.exception.GlobalExceptionHandler;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;
import com.jotabank.api.services.TransferenciaFactoryService;


@RestController
@RequestMapping("v1/api/transfer")
public class TransferenciaController {
	@Autowired
	private TransferenciaFactoryService serviceTransf;
	
	@PostMapping("/pix/{idConta}")
	public ResponseEntity<?> transfPix(@RequestBody DtoTransferRequest request, @PathVariable Long idConta) throws Exception{
	
			if(request.getCpf().isBlank() || request.getCpf().isEmpty()
				|| request.getCpf().matches("^[\\\\\\\\p{L}\\\\\\\\s]+$\\\\r\\\\n")) {
					GlobalExceptionHandler error = new GlobalExceptionHandler("Campo cpf está incorreto.", "404");
					return ResponseEntity.badRequest().body(error);
				}
				
				DtoTransferResponse response =  serviceTransf.transfePix(idConta, request.getCpf(), request.getValorTranferencia());				
				return ResponseEntity.ok().body(response);

	}
	
	@PostMapping("/ted/{idConta}")
	public ResponseEntity<?> transfTed(@RequestBody DtoTransferRequest request, @PathVariable Long idConta) throws ValidacaoInsercaoTransferencia{
		LocalTime hora = LocalTime.now();
		
		if(hora.getHour() >= 10 && hora.getHour() < 18) {
			
			return ResponseEntity.ok().body(serviceTransf.transfeTed(idConta, request.getNumConta(), 
					request.getCpf(), request.getValorTranferencia()));
			
		}else {
			GlobalExceptionHandler error = new GlobalExceptionHandler("404", "Essa funcionalidade só funciona das 10hrs até as 18hrs.");
			return ResponseEntity.badRequest().body(error);
		}
		
	}
	
	@PostMapping("/saque/{idConta}")
	public ResponseEntity<DtoTransferResponse> transfSaque(@RequestBody DtoSaqueRequest request, @PathVariable Long idConta){
		
		
		
		serviceTransf.transfeSaque(request.getValor() , idConta);
		
		return null;
	}
	
	@PostMapping("/deposito")
	public ResponseEntity<DtoTransferResponse> transfdeposito(@RequestBody DtoTransferRequest request){
		return null;
	} 
	
	@GetMapping("/historico/{cpf}")
	public ResponseEntity<?> getHistoricoTransferencia (@PathVariable("cpf") String cpf) throws ValidacaoDadosPessoa{
		
		System.out.print("Acessou a rota!");
		return ResponseEntity.status(HttpStatus.FOUND).body(serviceTransf.getHistoricoTransf(cpf));
		
	}
	

}
