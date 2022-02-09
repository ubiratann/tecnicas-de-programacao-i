package model;
public class Conjuncao extends Conectivo {
	public Conjuncao (Formula esq, String sinal, Formula dir) {
		 super(esq, sinal, dir);
	}

	public boolean obterValor() {
		return obterFilho(0).obterValor() & obterFilho(1).obterValor();
	}
}