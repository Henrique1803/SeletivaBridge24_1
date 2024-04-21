package com.SeletivaBridge24_1.SeletivaBridge24_1.util;

// ATENÇÂO! Esse algoritmo não foi criado por mim.
// Vou utilizá-lo como uma das possíveis maneiras de calcular a quantidade de números primos com o padrão
// de projeto Strategy.
// Referência utilizada para esse algoritmo:
// https://acervolima.com/peneira-de-atkin/

public class AlgoritmoCrivoDeAtkin implements AlgoritmoContagemDePrimos{
	public int calcularPrimosMenoresQueN(int numero) {
		int limite = numero;
		
		if (limite < 0) {
			return 0;
		}
		
		int quantidadePrimos = 0;

        // 2 e 3 são conhecidos como primos
        if (limite > 2)
            quantidadePrimos++;
        
        if (limite > 3)
            quantidadePrimos++;
        
        // Inicializa o array de crivo com valores falsos
        boolean crivo[] = new boolean[limite];
        
        for (int i = 0; i < limite; i++)
            crivo[i] = false;
        
        /* Marca crivo[n] como verdadeiro se uma das
        seguintes condições for verdadeira:
        a) n = (4*x*x) + (y*y) tem um número ímpar
           de soluções, isto é, existe um número ímpar
           de pares distintos (x, y) que satisfazem a equação
           e n % 12 = 1 ou n % 12 = 5.
        b) n = (3*x*x) + (y*y) tem um número ímpar
           de soluções e n % 12 = 7.
        c) n = (3*x*x) - (y*y) tem um número ímpar
           de soluções, x > y e n % 12 = 11 */
        for (int x = 1; x * x < limite; x++) {
            for (int y = 1; y * y < limite; y++) {
                // Parte principal do Crivo de Atkin
                int n = (4 * x * x) + (y * y);
                if (n <= limite && (n % 12 == 1 || n % 12 == 5))
                    crivo[n] ^= true;
                
                n = (3 * x * x) + (y * y);
                if (n <= limite && n % 12 == 7)
                    crivo[n] ^= true;
                
                n = (3 * x * x) - (y * y);
                if (x > y && n <= limite && n % 12 == 11)
                    crivo[n] ^= true;
            }
        }
        
        // Marca todos os múltiplos de quadrados como
        // não primos
        for (int r = 5; r * r < limite; r++) {
            if (crivo[r]) {
                for (int i = r * r; i < limite; i += r * r)
                    crivo[i] = false;
            }
        }
        
        // Soma os primos usando o array crivo[]
        for (int a = 5; a < limite; a++) {
            if (crivo[a]) {
                quantidadePrimos++;
            }
        }
        
        return quantidadePrimos;
	}
}
