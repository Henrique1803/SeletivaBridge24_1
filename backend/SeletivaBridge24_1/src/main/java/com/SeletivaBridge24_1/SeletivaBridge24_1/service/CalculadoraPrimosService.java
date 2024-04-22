package com.SeletivaBridge24_1.SeletivaBridge24_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.SeletivaBridge24_1.SeletivaBridge24_1.DAO.ICalculadoraPrimos;
import com.SeletivaBridge24_1.SeletivaBridge24_1.model.CalculadoraPrimos;

@Lazy
@Service
public class CalculadoraPrimosService {
	
	private ICalculadoraPrimos repository;
	
	public CalculadoraPrimosService(ICalculadoraPrimos repository) {
		this.repository = repository;
	}
	
	public List<CalculadoraPrimos> listarCalculadoraPrimos() {
		return repository.findAll();
	}
	
	public CalculadoraPrimos criarCalculadoraPrimos(CalculadoraPrimos calculadoraPrimos) {
		calculadoraPrimos.calcularPrimosMenoresQueN();
		return repository.save(calculadoraPrimos);
	}
	
	public CalculadoraPrimos editarCalculadoraPrimos(CalculadoraPrimos calculadoraPrimos) {
		calculadoraPrimos.calcularPrimosMenoresQueN();
		return repository.save(calculadoraPrimos);
	}
	
	public Boolean excluirCalculadoraPrimos(Integer id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Erro na exclusão do elemento com ID " + id + ". Erro: " + e.getMessage());
		}
	}

	public CalculadoraPrimos buscarCalculadoraPrimos(Integer id) {
		Optional<CalculadoraPrimos> calculadoraPrimos = repository.findById(id);
		return calculadoraPrimos.orElse(null);
		/*return calculadoraPrimos.orElseThrow(() -> new RuntimeException(
				"CalculadoraPrimos não encontrada! Id: " + id + ", Tipo: " + CalculadoraPrimos.class.getName()));*/
	}
	
}
