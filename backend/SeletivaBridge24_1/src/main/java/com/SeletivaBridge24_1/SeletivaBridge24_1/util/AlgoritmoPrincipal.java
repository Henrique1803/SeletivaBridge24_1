package com.SeletivaBridge24_1.SeletivaBridge24_1.util;

/**
 * Classe que fornece o algoritmo criado por mim para fazer o cálculo da quantidade
 * de números primos menores que N.
 */
public class AlgoritmoPrincipal implements AlgoritmoContagemDePrimos{
	
	public int calcularPrimosMenoresQueN(int numero) {
		int quantidadePrimos = 0;
		for (int i = 2; i < numero; i++) { //Itera do 2 até o limite-1, verificando se cada número é um primo
            if (isPrimo(i)) {
                quantidadePrimos++;
            }
        }
		return quantidadePrimos;
	}
	
	private boolean isPrimo(int num) {
		if (num <= 1) { // Caso a entrada seja menor ou igual a 1 (incluindo os números negativos)
			return false;
		}
		// Começa no 2 e itera até a raiz quadrada do número
		// Se o número não for primo, ele será divisível por algum número até a sua raiz quadrada
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true; // Caso não seja divisível por algum número até sua raiz quadrada, então ele é primo
	}
}
