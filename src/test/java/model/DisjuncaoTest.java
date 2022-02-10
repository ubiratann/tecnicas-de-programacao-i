package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisjuncaoTest {

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
	void testValorDisjuncaoVV() {
		A.trocarValor();
		B.trocarValor();
		
		Disjuncao disjuncao = new Disjuncao(A, "+", B);
		assertEquals(true, disjuncao.getValor());
	}
	
	@Test
	void testValorDisjuncaoVF() {
		A.trocarValor();
		
		Disjuncao disjuncao = new Disjuncao(A, "+", B);
		assertEquals(true, disjuncao.getValor());
	}
	
	@Test
	void testValorDisjuncaoFF() {
		Disjuncao disjuncao = new Disjuncao(A, "+", B);
		assertEquals(false, disjuncao.getValor());
	}
}
