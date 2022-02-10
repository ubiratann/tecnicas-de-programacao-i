package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Atomica;
import model.Conjuncao;
import model.Disjuncao;
import model.Formula;
import model.Negacao;

public class LeitorTest {

    @Test
    void testFimSubFormulaSimplesSemNegacao() {
	String formula = "A+B(C+D)";
	Leitor leitor = new Leitor(formula);

	int fim = leitor.fimSubFormula(leitor.getArrayPsi(), 2);
	assertEquals(formula.indexOf(")"), fim);

    }

    @Test
    void testFimSubFormulaSimplesComNegacao() {
	String formula = "A+B(C+D)'";
	Leitor leitor = new Leitor(formula);

	int fim = leitor.fimSubFormula(leitor.getArrayPsi(), 2);
	assertEquals(formula.indexOf("'"), fim);

    }

    @Test
    void testFimSubFormulaCompostaSemNegacao() {
	String formula = "A+B(C+(D.E))";
	Leitor leitor = new Leitor(formula);

	int fim = leitor.fimSubFormula(leitor.getArrayPsi(), 4);
	assertEquals(formula.indexOf(")"), fim);

    }

    @Test
    void testFimSubFormulaCompostaComNegacao() {
	String formula = "A+B((C+D)')";

	Leitor leitor = new Leitor(formula);

	int fim = leitor.fimSubFormula(leitor.getArrayPsi(), 4);
	assertEquals(formula.indexOf("'"), fim);

    }
    
    @Test
    void testCriarFormulaAtomica() {
	Atomica A = new Atomica("A");
	String formula = "A";
	
	Leitor leitor = new Leitor(formula);
	Formula resultado = leitor.criarFormula("A");
   
	assertEquals(true, (A.getClass() == resultado.getClass() && A.getValor() == resultado.getValor()));
    }
    

    @Test
    void testCriarFormulaConectivoDisjuncao() {
	String formula = "A+B";
	
	Leitor leitor = new Leitor(formula);
	Formula resultado = leitor.criarFormula("+");
   
	assertEquals(true, Disjuncao.class == resultado.getClass());	
    }
    
    @Test
    void testCriarFormulaConectivoConjuncao() {
	String formula = "A.B";
	
	Leitor leitor = new Leitor(formula);
	Formula resultado = leitor.criarFormula(".");
   
	assertEquals(true, Conjuncao.class == resultado.getClass());	
    }
    
    
    @Test
    void testLerFomulaSimplesSemNegacao() {
	Leitor leitor = new Leitor("A+B+C");
	
	Formula resultado = leitor.lerFormula();
	assertEquals(true, (resultado.getEsquerdo().getClass() == Disjuncao.class && resultado.getDireito().getClass() == Atomica.class));
    }
    
    @Test
    void testLerFomulaCompostaSemNegacao() {
	Leitor leitor = new Leitor("A.B+C");
	
	Formula resultado = leitor.lerFormula();
	assertEquals(true, (resultado.getEsquerdo().getClass() == Conjuncao.class && resultado.getDireito().getClass() == Atomica.class));
    }
    
    @Test
    void testLerFomulaSimplesComNegacao() {
	Leitor leitor = new Leitor("A+(B+C)'");
	
	Formula resultado = leitor.lerFormula();
	assertEquals(true, (resultado.getEsquerdo().getClass() == Atomica.class && resultado.getDireito().getClass() == Negacao.class));
    }
    
    @Test
    void testLerFomulaCompostaComNegacao() {
	Leitor leitor = new Leitor("A.(B+C)'");
	
	Formula resultado = leitor.lerFormula();
	assertEquals(true, (resultado.getEsquerdo().getClass() == Atomica.class && resultado.getDireito().getClass() == Negacao.class));
    }
}
