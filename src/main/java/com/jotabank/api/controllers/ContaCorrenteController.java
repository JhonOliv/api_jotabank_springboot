package com.jotabank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.services.ContaCorrenteService;

@RestController
@RequestMapping("/api/helloWorld")
public class ContaCorrenteController {

	@Autowired
	private ContaCorrenteService servContaCorrente;
	
	@GetMapping
	public String helloWorld() {
		return servContaCorrente.testHelloWorld("Jhonatan Silva");
	}
}
