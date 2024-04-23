package com.SeletivaBridge24_1.SeletivaBridge24_1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SeletivaBridge24_1.SeletivaBridge24_1.model.CalculadoraPrimos;
import com.SeletivaBridge24_1.SeletivaBridge24_1.service.CalculadoraPrimosService;
import com.SeletivaBridge24_1.SeletivaBridge24_1.util.Constantes;

import jakarta.validation.Valid;

/**
 * Esta classe é um controlador REST responsável por lidar com operações relacionadas à calculadora de números primos.
 * Ele fornece endpoints para listar, criar, editar e excluir instâncias de CalculadoraPrimos. 
 * Além disso, há um método para buscar um objeto CalculadoraPrimos por ID e 
 * um método para retornar o limite máximo suportado pela calculadora.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/Calculadora_primos")
public class CalculadoraPrimosController {
	@Autowired
	private CalculadoraPrimosService calculadoraPrimosService;
	
	public CalculadoraPrimosController(CalculadoraPrimosService calculadoraPrimosService) {
		this.calculadoraPrimosService = calculadoraPrimosService;
	}
	
	@GetMapping
	public ResponseEntity<List<CalculadoraPrimos>> listaCalculadoraPrimos () {
		return ResponseEntity.status(HttpStatus.OK).body(calculadoraPrimosService.listarCalculadoraPrimos());
	}
	
	@PostMapping
	public ResponseEntity<CalculadoraPrimos> criarCalculadoraPrimos(@Valid @RequestBody CalculadoraPrimos calculadoraPrimos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calculadoraPrimosService.criarCalculadoraPrimos(calculadoraPrimos));
	}
	
	@PutMapping
	public ResponseEntity<CalculadoraPrimos> editarCalculadoraPrimos(@Valid @RequestBody CalculadoraPrimos calculadoraPrimos) {
		return ResponseEntity.status(HttpStatus.OK).body(calculadoraPrimosService.editarCalculadoraPrimos(calculadoraPrimos));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCalculadoraPrimos(@PathVariable Integer id) {
		calculadoraPrimosService.excluirCalculadoraPrimos(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CalculadoraPrimos> buscarCalculadoraPrimos(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(calculadoraPrimosService.buscarCalculadoraPrimos(id));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		return errors;
	}
	
	@GetMapping("/limite")
	public int numeroMaxSuportado () {
		return Constantes.NUMERO_MAX_SUPORTADO;
	}
	
}
