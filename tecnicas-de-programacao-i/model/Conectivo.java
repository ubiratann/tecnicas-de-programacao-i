abstract class Conectivo extends Formula {
	protected String sinal;
	protected Formula esquerdo, direito;

	Conectivo (Formula esq, String sinal) {
		this.esquerdo = esq;
		this.direito = null;
		this.sinal = sinal;
	}

	Conectivo (Formula esq, String sinal, Formula dir) {
		this.esquerdo = esq;
		this.direito = dir;
		this.sinal = sinal;
	}

	abstract boolean obterValor();

	// segue a ideia de adicionar à direita do sinal
	void add(Formula a) {
		if (esquerdo == null) {
			esquerdo = a;
		} 
		else {
			direito = a;
		}
	}

	void add(Formula a, Formula b) {
		esquerdo = a;
		direito = b;
	}

	void remove(Formula a) {
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

	// como é um arvore binaria, 0 para o filho esquerdo e 1 para o filho direito
	Formula obterFilho(int index) {
		if (index == 0) {
			return esquerdo;
		}
		else if (index == 1) {
			return direito;
		}
		else {
			return null;
		}
	}

	String obterExpressao() {
		return esquerdo+sinal+direito;
	}

	public String toString() {
		return esquerdo+sinal+direito;
	}
        
       
}