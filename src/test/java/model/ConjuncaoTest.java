package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuncaoTest {

	private Atomica A;
	private Atomica B;
	
	@BeforeEach
	void up() {
		A = new Atomica("A");
		B = new Atomica ("B");
	}
	
	@AfterEach
	void down() {
		A = null;
		B = null;
	}
	
	@Test
	void testValorConjuncaoVV() {		
		A.trocarValor();
		B.trocarValor();
		
		Conjuncao conjuncao = new Conjuncao(A, ".", B);
		assertEquals(conjuncao.getValor(), true);
	}
	
	@Test
	void testValorConjuncaoVF() {
		A.trocarValor();
		
		Conjuncao conjuncao = new Conjuncao(A, ".", B);
		assertEquals(conjuncao.getValor(), false);
	}

	@Test
	void testValorConjuncaoFF() {
		Conjuncao conjuncao = new Conjuncao(A, ".", B);
		assertEquals(conjuncao.getValor(), false);
	}
	


}
