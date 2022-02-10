package model;

public class Disjuncao extends Conectivo {
	
	public Disjuncao(Formula esq, String sinal, Formula dir) {
		super(esq, sinal, dir);
	}

	@Override
	public boolean getValor() {
		return getEsquerdo().getValor() | getDireito().getValor();
	}

}