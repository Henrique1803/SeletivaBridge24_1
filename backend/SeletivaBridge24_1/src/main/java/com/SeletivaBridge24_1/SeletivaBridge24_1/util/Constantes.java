package com.SeletivaBridge24_1.SeletivaBridge24_1.util;

/**
 * Classe que fornece as constantes do sistema.
 */
public class Constantes {
    // Número máximo suportado pelo servidor para o cálculo
    public static final int NUMERO_MAX_SUPORTADO = 10000000;
    // Padrão de projeto Strategy permite a troca dinâmica do algoritmo utilizado para contagem dos números primos menores que N
    public static final AlgoritmoContagemDePrimos ALGORITMO_PADRAO = new AlgoritmoPrincipal();
}
