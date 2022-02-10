package model;

public class Negacao extends Conectivo {

	public Negacao (Formula esq, String sinal) {
		super(esq,sinal);
	}

	@Override
	public boolean getValor() {
		return !getEsquerdo().getValor(); 
	}
	
	@Override
	public String toString() {
		return "("+esquerdo+")"+sinal;
	}
}