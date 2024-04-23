package com.SeletivaBridge24_1.SeletivaBridge24_1.util;

/**
 * ATENÇÂO! Esse algoritmo não foi criado por mim.
 * Vou utilizá-lo como uma das possíveis maneiras de calcular a quantidade de números primos com o padrão de projeto Strategy.
 * Referência utilizada para esse algoritmo:
 * https://acervolima.com/peneira-de-eratostenes/
 */
public class AlgoritmoCrivoDeEratostenes implements AlgoritmoContagemDePrimos {
	public int calcularPrimosMenoresQueN(int numero) {
		int n = numero - 1;
		if (n <= 0) {
			return 0;
		}
		int quantidadePrimos = 0;
		// Cria um array booleano
		// "primo[0..n]" e
		// inicializa todas as entradas
		// como verdadeiras. Um valor em
		// primo[i] será finalmente
		// falso se i não for um
		// primo, senão verdadeiro.
		boolean primo[] = new boolean[n + 1];
		for (int i = 0; i <= n; i++)
			primo[i] = true;

		for (int p = 2; p * p <= n; p++) {
			// Se primo[p] não for alterado, então é um
			// primo
			if (primo[p] == true) {
				// Atualiza todos os múltiplos de p
				for (int i = p * p; i <= n; i += p)
					primo[i] = false;
			}
		}

		// Imprime todos os números primos
		for (int i = 2; i <= n; i++) {
			if (primo[i] == true)
				quantidadePrimos++;
		}

		return quantidadePrimos;
	}
}
