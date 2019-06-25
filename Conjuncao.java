class Conjuncao extends Conectivo {
	Conjuncao (Formula esq, String sinal, Formula dir) {
		 super(esq, sinal, dir);
	}

	boolean obterValor() {
		return obterFilho(0).obterValor() & obterFilho(1).obterValor();
	}
}