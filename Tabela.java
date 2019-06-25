import java.util.*;

public class Tabela {
	String stringPhi;
	Formula phi;
	Atomica[] atomica;
	
	Tabela (Formula form, Atomica[] at, String nome) {
		phi = form;
		atomica = at;
		stringPhi = nome;

		inicializar();
	} 
	
	void inicializar() {
		if (atomica != null) for (int i = 0; i < atomica.length; i++) atomica[i].escolherBitPos(i); // a posicao da prop at no array corresponde a sua pos no num bin
	}

	void mostrarCabecalho() {
		if (atomica != null)
		{
			for (int i = atomica.length-1; i >= 0; i--) System.out.print( atomica[i]+"\t"); // 0 corresponde ao LBS (menos sig); atomica.length-1 ao MBS (mais sig)
			System.out.println(stringPhi);
		}
	}
	// mostra os val verd das prop atomicas e da formula 
	void mostrarLinha() {
		if (atomica != null)
		{
			for (int i = atomica.length-1; i >= 0; i--) System.out.print( atomica[i].obterValor()+"\t");
			System.out.println(phi.obterValor());
		}
	}

	void incrementarValoresAt() {
		if (atomica != null) for (Atomica prop : atomica) prop.incrementar(); // incrementa o contador das prop at, uma por uma
	}

	void verificarValoresAt() {
		if (atomica != null)
			for (Atomica prop : atomica)
			{
				if (prop.obterContador() == prop.obterContMax()) // troca os valores das prop at cujo esteja com o contador no limite
				{
					prop.zerarCont();
					prop.trocarValor();
				}
			}
	}

	void gerarTabela() {
		int linha, qtdTotalLinhas;
			
		linha = 1;
		qtdTotalLinhas = 2 * atomica[ atomica.length-1 ].obterContMax(); // o total de linhas da tab verd corresponde a 2 elevado a pos do MSB;
		
		mostrarCabecalho();

		while (linha <= qtdTotalLinhas)
		{
			mostrarLinha();
			
			incrementarValoresAt();
			
			verificarValoresAt();
			
			linha++;
		}
	}
}


/* TABELA ANTIGA 
import java.util.*;

public class Tabela {
	Formula phi;
	ArrayList <Atomica> atomica = new ArrayList();

	Tabela (Formula form, ArrayList<Atomica> at) {
            this.phi = form;
            this.atomica = at;

            inicializar();
	} 
	
	void inicializar() {
		for (int i = 0; i < atomica.size(); i++) atomica.get(i).escolherBitPos(i); // a posicao da prop at no array corresponde a sua pos no num bin
	}

	void mostrarCabecalho() {
		for (int i = atomica.size()-1; i >= 0; i--) System.out.print( atomica.get(i)+"\t"); // 0 corresponde ao LBS (menos sig); atomica.length-1 ao MBS (mais sig)
		System.out.println(phi);
	}
	// mostra os val verd das prop atomicas e da formula 
	void mostrarLinha() {
		for (int i = atomica.size()-1; i >= 0; i--) System.out.print( atomica.get(i).obterValor()+"\t");
		System.out.println(phi.obterValor());
	}

	void incrementarValoresAt() {
		for (Atomica prop : atomica) prop.incrementar(); // incrementa o contador das prop at, uma por uma
	}

	void verificarValoresAt() {
		for (Atomica prop : atomica)
		{
			if (prop.obterContador() == prop.obterContMax()) // troca os valores das prop at cujo esteja com o contador no limite
			{
				prop.zerarCont();
				prop.trocarValor();
			}
		}
	}

	void gerarTabela() {
		int linha, qtdTotalLinhas;
			
		linha = 1;
		qtdTotalLinhas = 2 * atomica.get(atomica.size()-1).obterContMax(); // o total de linhas da tab verd corresponde a 2 elevado a pos do MSB;
		
		mostrarCabecalho();

		while (linha <= qtdTotalLinhas)
		{
			mostrarLinha();
			
			incrementarValoresAt();
			
			verificarValoresAt();
			
			linha++;
		}
	}
}*/