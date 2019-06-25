class Negacao extends Conectivo {
	Negacao (Formula esq, String sinal) {
		super(esq,sinal);
	}

	boolean obterValor() {
		return !obterFilho(0).obterValor(); 
	}
}