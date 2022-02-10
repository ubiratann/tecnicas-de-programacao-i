package model;
public abstract class Conectivo extends Formula {
	protected String sinal;
	protected Formula esquerdo, direito;

	public Conectivo (Formula esq, String sinal) {
		this.esquerdo = esq;
		this.direito = null;
		this.sinal = sinal;
	}

	public Conectivo (Formula esq, String sinal, Formula dir) {
		this.esquerdo = esq;
		this.direito = dir;
		this.sinal = sinal;
	}

	@Override
	public abstract boolean getValor();

	@Override
	public void add(Formula a) {
		if (esquerdo == null) {
			esquerdo = a;
		} 
		else {
			direito = a;
		}
	}

	public void add(Formula a, Formula b) {
		esquerdo = a;
		direito = b;
	}

	@Override
	public void remove(Formula a) {
		if (esquerdo == a) {
			esquerdo = null;
		}
		else if (direito == a) {
			direito = null;
		}
		else {
			System.out.println("Error");
		}
	}

	public String obterExpressao() {
		return esquerdo+sinal+direito;
	}
	
	@Override
	public String toString() {
		return esquerdo+sinal+direito;
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}
	
	@Override
	public Formula getEsquerdo() {
		return esquerdo;
	}

	@Override
	public Formula getDireito() {
		return direito;
	}

	
}