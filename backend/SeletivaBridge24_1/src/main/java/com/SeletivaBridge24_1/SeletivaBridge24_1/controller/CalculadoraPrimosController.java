package com.SeletivaBridge24_1.SeletivaBridge24_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SeletivaBridge24_1.SeletivaBridge24_1.DAO.ICalculadoraPrimos;
import com.SeletivaBridge24_1.SeletivaBridge24_1.model.CalculadoraPrimos;

@RestController
@CrossOrigin("*")
@RequestMapping("/Calculadora_primos")
public class CalculadoraPrimosController {
	
	@Autowired
	private ICalculadoraPrimos dao;
	
	@GetMapping
	public List<CalculadoraPrimos> listaCalculadoraPrimos () {
		return (List<CalculadoraPrimos>) dao.findAll();
	}
	
	@PostMapping
	public CalculadoraPrimos criarCalculadoraPrimos(@RequestBody CalculadoraPrimos calculadoraPrimos) {
		calculadoraPrimos.calcularPrimosMenoresQueN();
		CalculadoraPrimos calculadoraPrimosNova = dao.save(calculadoraPrimos);
		return calculadoraPrimosNova;
	}
	
	@PutMapping
	public CalculadoraPrimos editarCalculadoraPrimos(@RequestBody CalculadoraPrimos calculadoraPrimos) {
		calculadoraPrimos.calcularPrimosMenoresQueN();
		CalculadoraPrimos calculadoraPrimosNova = dao.save(calculadoraPrimos);
		return calculadoraPrimosNova;
	}
	
	@DeleteMapping("/{id}")
	public Optional<CalculadoraPrimos> excluirCalculadoraPrimos(@PathVariable Integer id) {
		Optional<CalculadoraPrimos> calculadoraPrimos = dao.findById(id);
		dao.deleteById(id);
		return calculadoraPrimos;
	}
	
}
