package com.SeletivaBridge24_1.SeletivaBridge24_1.model;

import com.SeletivaBridge24_1.SeletivaBridge24_1.util.Constantes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Esta classe representa a entidade CalculadoraPrimos. Ela inclui um número, 
 * a quantidade de primos menores que esse número (intervalo aberto: não inclui o número limite) e
 * o tempo utilizado para o cálculo, além de algumas validações.
 * Ainda,  utiliza-se o padrão de projeto Strategy. Um objeto do tipo AlgoritmoContagemDePrimos
 * é necessário para realizar a contagem, permitindo que o algoritmo seja trocado dinamicamente, aplicando herança.
 * Assim é possível adicionar novas implementações de algoritmos sem modificar o código, testando a eficiência de cada um.
 */
@Entity
@Table(name = "Calculadora_primos")
public class CalculadoraPrimos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@PositiveOrZero(message = "O número não pode ser negativo!")
    @Max(value = Constantes.NUMERO_MAX_SUPORTADO, message = "Nossos servidores conseguem suportar essa operação somente até o número " + Constantes.NUMERO_MAX_SUPORTADO)
	@Column(name = "numero", nullable = false)
	private int numero;
	
	@PositiveOrZero(message = "Quantidade de primos não pode ser negativo!")
	@Column(name = "quantidade_primos", nullable = false)
	private int quantidadePrimos;
	
	@PositiveOrZero(message = "Tempo utilizado não pode ser negativo!")
	@Column(name = "tempo_utilizado", nullable = false)
	private long tempoUtilizado;

	public void calcularPrimosMenoresQueN() {
		long startTime = System.nanoTime(); // Início do contador
		
		// Algoritmo padrão é definido nas constantes e utilizado aqui.
		// Para trocar dinamicamente o algoritmo, basta alterar o objeto para o algoritmo desejado na classe de constantes.
		quantidadePrimos = Constantes.ALGORITMO_PADRAO.calcularPrimosMenoresQueN(numero);
		
		long endTime = System.nanoTime(); // Fim do contador
		tempoUtilizado = endTime - startTime; // Tempo gasto para o cálculo
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getQuantidadePrimos() {
		return quantidadePrimos;
	}

	public void setQuantidadePrimos(int quantidadePrimos) {
		this.quantidadePrimos = quantidadePrimos;
	}

	public long getTempoUtilizado() {
		return tempoUtilizado;
	}

	public void setTempoUtilizado(long tempoUtilizado) {
		this.tempoUtilizado = tempoUtilizado;
	}
	
}
