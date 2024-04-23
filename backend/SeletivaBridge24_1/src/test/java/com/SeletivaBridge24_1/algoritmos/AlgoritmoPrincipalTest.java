package com.SeletivaBridge24_1.algoritmos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.SeletivaBridge24_1.SeletivaBridge24_1.util.AlgoritmoPrincipal;

public class AlgoritmoPrincipalTest {
	
	private static AlgoritmoPrincipal algoritmo;
	
	@BeforeAll
	public static void initCalculatorService() {
		algoritmo = new AlgoritmoPrincipal();
	}
	
	@Test
	@DisplayName("Teste do algoritmo principal com número negativo")
	public void testaParametroNegativo() {
		assertEquals(0, algoritmo.calcularPrimosMenoresQueN(-1));
	}
	
	@Test
	@DisplayName("Teste do algoritmo principal com número zero")
	public void testaParametroZero() {
		assertEquals(0, algoritmo.calcularPrimosMenoresQueN(0));
	}
	
	@Test
	@DisplayName("Teste do algoritmo principal com número pequeno")
	public void testaParametroPequeno() {
		assertEquals(4, algoritmo.calcularPrimosMenoresQueN(10));
	}
	
	@Test
	@DisplayName("Teste do algoritmo principal com número médio")
	public void testaParametroMedio() {
		assertEquals(1229, algoritmo.calcularPrimosMenoresQueN(10000));
	}
	
	@Test
	@DisplayName("Teste do algoritmo principal com número grande")
	public void testaParametroGrande() {
		assertEquals(664579, algoritmo.calcularPrimosMenoresQueN(10000000));
	}
	
}