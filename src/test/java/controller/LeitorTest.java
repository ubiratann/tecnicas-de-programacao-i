package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Atomica;
import model.Conectivo;
import model.Conjuncao;
import model.Disjuncao;
import model.Formula;

public class LeitorTest {

    @Test
    void testCriarFormulaAtomica() {
	Formula formulaAtomica = new Atomica("A");
	assertEquals(false, formulaAtomica.obterValor());
    }
    

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
}
