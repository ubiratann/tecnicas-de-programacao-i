package model;

public class Conjuncao extends Conectivo {
	
	public Conjuncao (Formula esq, String sinal, Formula dir) {
		 super(esq, sinal, dir);
	}
	
	@Override
	public boolean getValor() {
		return getEsquerdo().getValor() & getDireito().getValor();
	}

}