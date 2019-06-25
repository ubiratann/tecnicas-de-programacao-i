package model;
public class Negacao extends Conectivo {
	public Negacao (Formula esq, String sinal) {
		super(esq,sinal);
	}

	public boolean obterValor() {
		return !obterFilho(0).obterValor(); 
	}
}